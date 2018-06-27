package io.github.vftdan.vftdankeybinder;

public class GameOutputHandleFunction implements IHandleFunction {
	private String outStr;
	public void execute() {
		System.out.println(outStr);
	}
	public GameOutputHandleFunction(String s) {
		outStr = s;
	}
}
