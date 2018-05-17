package com.company.quisy;

import com.google.common.collect.Iterables;
import com.google.gson.Gson;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.math3.util.Precision.round;

public class Main {


    public static class MyMapper extends Mapper<Object, Text, Text, DoubleWritable> {

        private DoubleWritable result = new DoubleWritable();
        private Text word = new Text();

        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

            Feature feature = new Gson().fromJson(value.toString(), Feature.class);
            Features2d features2d = feature.getFeatures2D();

            String side = feature.getSide();
            int series = feature.getSeries();
            word.set(String.format("%d-%s-first", series, side));
            result.set(roundDobule(features2d.getFirst()));
            context.write(word, result);
            word.set(String.format("%d-%s-second", series, side));
            result.set(roundDobule(features2d.getSecond()));
            context.write(word, result);
            word.set(String.format("%d-%s-third", series, side));
            result.set(roundDobule(features2d.getThird()));
            context.write(word, result);
            word.set(String.format("%d-%s-fourth", series, side));
            result.set(roundDobule(features2d.getFourth()));
            context.write(word, result);
            word.set(String.format("%d-%s-fifth", series, side));
            result.set(roundDobule(features2d.getFifth()));
            context.write(word, result);
        }

        private double roundDobule(double value) {
            return round(value, 2);
        }
    }

    public static class MyReducer extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {
        private DoubleWritable result = new DoubleWritable();
        private int size = 0;

        public void reduce(Text key, Iterable<DoubleWritable> values, Context context) throws IOException, InterruptedException {
            List<Double> list = new ArrayList<>();
            for (DoubleWritable value : values) {
                double val = value.get();
                ((ArrayList) list).add(val);
            }

            size = list.size();
            double stdDev = getStdDev(list);
            result.set(stdDev);
            context.write(key, result);
        }

        private double getMean(List<Double> values) {
            double sum = 0.0;
            for (double value : values) {
                sum += value;
            }
            return sum / size;
        }

        private double getVariance(List<Double> values) {
            double mean = getMean(values);
            double temp = 0;
            for (double value : values) {
                temp += (value - mean) * (value - mean);
            }
            return temp / (size - 1);
        }

        private double getStdDev(List<Double> values) {
            return Math.sqrt(getVariance(values));
        }
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        PropertyConfigurator.configure(Main.class.getResource("slf4j.properties"));
        Job job = Job.getInstance(conf, "test");
        job.setJarByClass(Main.class);
        job.setMapperClass(MyMapper.class);
        //job.setCombinerClass(MyReducer.class);
        job.setReducerClass(MyReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(DoubleWritable.class);

        FileInputFormat.setInputDirRecursive(job, true);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
