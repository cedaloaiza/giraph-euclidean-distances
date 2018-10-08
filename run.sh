
rm -R distances
mvn package

$GIRAPH_HOME/bin/giraph target/euclidean-distances-1.0-SNAPSHOT-jar-with-dependencies.jar  \
ru.simple.giraph.project.org.EuclideanDistancesComputation \
-vif ru.simple.giraph.project.org.PointsVertexInputFormat \
-vip points_input.txt \
-vof org.apache.giraph.io.formats.IdWithValueTextOutputFormat \
-mc ru.simple.giraph.project.org.EmptyMasterCompute \
-op distances \
-ca giraph.SplitMasterWorker=false \
-ca giraph.logLevel=ERROR \
-w 1




