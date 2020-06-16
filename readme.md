spark-submit \
--class com.xiaodutv.controller.ETLStatisticCRMTest \
--master yarn \
--deploy-mode client \
--num-executors 6 \
--driver-memory 4g \
--executor-memory 14g \
--executor-cores 7 \
--conf spark.yarn.executor.memoryOverhead=2048 \
etlStatisticCRM-1.0-SNAPSHOT.jar \
$1 $2


#spark-etl

spark-submit \
 --class com.corp.car.etl.CarDataEtl \
 --master yarn \
 --deploy-mode cluster \
 --queue root.*** \
 --conf spark.conf.input="hdfs:****" \
 --conf spark.conf.config="hdfs:****/json_config_demo.json" \
 --conf spark.conf.output="hdfs:****" \
 --conf spark.conf.partNum=8 \
 --driver-memory 4g \
 --driver-cores 2 \
 --executor-memory 10g \
 --executor-cores 2 \
 --num-executors 8 \
 /home/q/username/car-data-etl-1.0-SNAPSHOT-jar-with-dependencies.jar




spark-submit  --class com.process.LogEtl  --master local[2]   --executor-memory 30g \
                                                              --executor-cores 7 \  --driver-memory 2g   MyScala-1.0-SNAPSHOT.jar  "bos://video-log/nsclick_log_adver/2018/03/05/06/bja-s02-dcc056-videoclick02.bj/*" "file:/home/video/jun.chang/test"

spark-submit \
--class  com.process.LogEtl \
--master yarn \
--deploy-mode client \
--num-executors 6 \
--driver-memory 4g \
--executor-memory 8g \
--executor-cores 7 \
--conf spark.yarn.executor.memoryOverhead=2048 \
MyScala-1.0-SNAPSHOT.jar \
"bos://video-log/nsclick_log_adver/2018/03/06/*/*/*" "bos://xiaodutv-adver/tmp/hive/zhangyong/test"



spark-submit  --class com.require.ReturnDu  --master local  \
--conf "spark.local.dir=/home/video/spark/data/tmp" --driver-memory 4g \
--executor-memory 50g \
--executor-cores 8  MyScala-1.0-SNAPSHOT.jar  "/home/video/allusers.txt"