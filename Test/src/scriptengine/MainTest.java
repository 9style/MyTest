package scriptengine;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainTest {
	public static void main(String[] args) throws ScriptException, FileNotFoundException, NoSuchMethodException {
		 try {
			ScriptEngine engine = new ScriptEngineManager()
					.getEngineByName("javascript");
			Compilable compilable = (Compilable) engine;
			Bindings bindings = engine.createBindings(); // Local级别的Binding
			String script = "function add(op1,op2){return op1+op2} add(a, b)"; //
			// 定义函数并调用
			CompiledScript JSFunction = compilable.compile(script); // 解析编译脚本函数
			bindings.put("a", 1);
			bindings.put("b", 2); // 通过Bindings加入参数
			Object result = JSFunction.eval(bindings);
			System.out.println(result); // 调用缓存着的脚本函数对象，Bindings作为参数容器传入
		} catch (ScriptException e) {
		}
		// showAll();
		// firstblood();
		secondblood();
		thirdblood();
		forthblood();
	}

	public static void showAll() {
		ScriptEngineManager factory = new ScriptEngineManager();
		for (ScriptEngineFactory available : factory.getEngineFactories()) {
			System.out.println(available.getEngineName());
		}
	}
/**
 * =====================================================================================
 * 解释执行脚本
 * =====================================================================================
 */
	/**
	 * java调用后缀为.js的文件
	 * 
	 * @throws ScriptException
	 * @throws FileNotFoundException
	 * @throws NoSuchMethodException
	 */
	public static void firstblood() throws ScriptException,
			FileNotFoundException, NoSuchMethodException {
		ScriptEngine engine = new ScriptEngineManager()
				.getEngineByName("javascript");
		engine.eval(new FileReader(
				"D://develop//eclipse-SDK-4.2.2-win32-x86_64//eclipse//workspace//Test//bin//test.js"));
	}

	/**
	 * java调用javascript中的方法
	 * 
	 * @throws NoSuchMethodException
	 * @throws ScriptException
	 */
	public static void secondblood() throws NoSuchMethodException,
			ScriptException {
		ScriptEngine engine = new ScriptEngineManager()
				.getEngineByName("javascript");
		String script = "function sum(first,second) { return first+second;}";
		engine.eval(script);
		Invocable in = (Invocable) engine;
		Object obj = in.invokeFunction("sum", 10, 20);
		System.out.println(obj.toString());
	}
	
	/**
	 * java通过接口调用javascript中的方法
	 * @throws ScriptException
	 */
	public static void thirdblood() throws ScriptException {
		ScriptEngine engine = new ScriptEngineManager()
				.getEngineByName("javascript");
		String script = "function max(first,second) { return (first>=second)?first:second;}";
		engine.eval(script);
		Invocable in = (Invocable) engine;
		MinUtil minUtil = in.getInterface(MinUtil.class);
		System.out.println(minUtil.max(22, 33));
	}

	public interface MinUtil {
		int max(int a, int b);
	}
	
/**
 * ==================================================================================
 * 编译后再运行
 * ==================================================================================
 * @throws ScriptException 
 */
	
	public static void forthblood() throws ScriptException {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");
		String script = "var email=/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]"
				+ "+(\\.[a-zA-Z0-9_-]+)+$/;";
		script += "var ip = /^(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])"
				+ "(\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])){3}$/;";
		script += "if(email.test(str)){println('it is an email')}"
				+ "else if(ip.test(str)){println('it is an ip address')}"
				+ "else{println('I don\\'t know')}";
		engine.put("str", "email@address.tony");
		Compilable compilable = (Compilable) engine;
		CompiledScript compiled = compilable.compile(script);
		compiled.eval();
	}
}
