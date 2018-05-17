#!/bin/bash

echo "-------- hadoop_start --------"

sh jps.sh
sh hdfs_format.sh
start-dfs.sh
start-yarn.sh
mr-jobhistory-daemon.sh start historyserver
sh jps.sh
