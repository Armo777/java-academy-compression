run: jar
	java -jar ./output/jcoursearchive.jar -c -input ddd.rr -output sdags.ru
jar: compile
	jar --create --file ./output/jcoursearchive.jar --main-class ws.academy.Main -C ./output/ .
compile: clear
	javac -d ./output ./src/main/java/ws/academy/*.java
clear:
	rm -rf ./output ./target
