#!/bin/bash
mkdir -p build
rm -rf build/*

CLASSPATH="BOOT-INF/classes"
for jar in BOOT-INF/lib/*.jar; do
    CLASSPATH="$CLASSPATH:$jar"
done

# We only compile the LicenseController.java
# We expect it to find dependencies in BOOT-INF/classes and BOOT-INF/lib
javac -cp "$CLASSPATH" -d build src/com/htmake/reader/api/controller/LicenseController.java

if [ $? -eq 0 ]; then
    echo "Compilation successful"
else
    echo "Compilation failed"
    exit 1
fi
