
mvn archetype:generate \
    -DarchetypeGroupId=org.apache.flink \
    -DarchetypeArtifactId=flink-quickstart-java \
    -DarchetypeVersion=1.6.1 \
    -DgroupId=com.data2 \
    -DartifactId=flink-official-demo \
    -Dversion=0.0.1 \
    -Dpackage=com.data2.flink.official.demo \
    -DinteractiveMode=false
