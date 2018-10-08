echo "Compiling..."
mvn package
echo "Removing output directory..."
hadoop dfs -rm -r exampleOut

echo "Executing..."
hadoop jar target/my-app-1.0-SNAPSHOT-jar-with-dependencies.jar org.apache.giraph.GiraphRunner ru.simple.giraph.project.org.EmptyComputation \
-vif org.apache.giraph.io.formats.IntIntNullTextVertexInputFormat \
-vip simple_int \
-vof org.apache.giraph.io.formats.IdWithValueTextOutputFormat \
-mc ru.simple.giraph.project.org.EmptyMasterCompute \
-op /user/$USER/exampleOut \
-w 2 \
-ca giraph.SplitMasterWorker=true \
-ca giraph.logLevel=DEBUG \
-ca mapreduce.jobtracker.address=yarn \
-ca giraph.useSuperstepCounters=false
