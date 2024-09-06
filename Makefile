compress: jar
	java -jar ./output/jcoursearchive.jar -c -input ./docs/romeo-and-juliet_Shakespeare.txt -output ./archived.arch

extract: jar
	java -jar ./output/jcoursearchive.jar -c -input ./archived.arch >> ./Romeo_and_Juliet.txt

jar: compile
	jar --create --file ./output/jcoursearchive.jar --main-class ws.academy.Main -C ./output/ .
compile: clear
	javac -d ./output ./src/main/java/ws/academy/*.java
clear:
	rm -rf ./output ./target
