<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-jar-plugin</artifactId>
            <version>3.2.0</version>
            <configuration>
                <archive>
                    <manifest>
                        <addClasspath>true</addClasspath>
                        <mainClass>your.main.Class</mainClass>
                    </manifest>
                </archive>
                <argLine>-Xmx2G -Dmy.property=value</argLine>
            </configuration>
        </plugin>
    </plugins>
</build>
