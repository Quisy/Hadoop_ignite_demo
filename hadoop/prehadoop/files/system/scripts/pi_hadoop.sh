#!/bin/bash

echo "-------- pi_hadoop --------"

num_maps=2
num_points=10
hadoop jar /usr/local/hadoop/share/hadoop/mapreduce/hadoop-mapreduce-examples-2.6.4.jar pi $num_maps $num_points
