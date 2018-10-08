echo "Compiling..."
mvn package
echo "Removing output directory..."
hadoop dfs -rm -r distances

echo "Executing..."
hadoop jar target/euclidean-distances-1.0-SNAPSHOT-jar-with-dependencies.jar  org.apache.giraph.GiraphRunner ru.simple.giraph.project.org.EuclideanDistancesComputation \
-vif ru.simple.giraph.project.org.PointsVertexInputFormat \
-vip points_input.txt \
-vof ru.simple.giraph.project.org.RDCMSTVertexOutputFormat \
-mc ru.simple.giraph.project.org.EmptyMasterCompute \
-op /user/$USER/distances \
-w 1 \
-ca giraph.SplitMasterWorker=true \
-ca giraph.logLevel=DEBUG \
-ca mapreduce.jobtracker.address=yarn \
-ca giraph.useSuperstepCounters=false
#-vip spain_locsGiraph.txt \

