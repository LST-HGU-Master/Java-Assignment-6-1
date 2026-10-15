import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * version (Nov11 2025) supports Handling the Absence of the static Modifier and Non-public Declarations
 * version 20261014 revised
 */ 

public class CalculatorTest {

    @Test
    public void testMultiply()
    {
        final String TARGET_METHOD_NAME = "multiply";
        final Class<?>[] TARGET_METHOD_PARAMS = new Class<?>[]{int.class, int.class};
        
        try {
            Method method = Calculator.class.getDeclaredMethod(TARGET_METHOD_NAME, TARGET_METHOD_PARAMS);
            method.setAccessible(true);

            // check if static modifier defined
            boolean isStatic = Modifier.isStatic(method.getModifiers());
            if  (!isStatic) {
                fail(TARGET_METHOD_NAME + " メソッドは static として宣言されている必要があります。");
            }

            // assertion
            Object resultObj = method.invoke(null, 4, 9);
            int actualResult = (int)resultObj;
            assertEquals(36,  actualResult, "積の計算結果が間違っています!");

            resultObj = method.invoke(null, -3, 5);
            assertEquals(-15, (int)resultObj, "multiply(-3, 5) の積の計算結果が間違っています!（負の数の計算を確認してください）");

            resultObj = method.invoke(null, 0, 7);
            assertEquals(0, (int)resultObj, "multiply(0, 7) の積の計算結果が間違っています!");

        } catch (NoSuchMethodException e){
            fail("メソッド " + TARGET_METHOD_NAME + "(int, int) が見つかりません。メソッド名や引数の型を確認してください。");
        } catch (Exception e) {
            fail("メソッド呼び出し中にエラーが発生しました。", e);
        }
    }

    @Test
    public void testDivide()
    {
        final String TARGET_METHOD_NAME = "divide";
        final Class<?>[] TARGET_METHOD_PARAMS = new Class<?>[]{int.class, int.class};
        
        try {
            Method method = Calculator.class.getDeclaredMethod(TARGET_METHOD_NAME, TARGET_METHOD_PARAMS);
            method.setAccessible(true);

            // check if static modifier defined
            boolean isStatic = Modifier.isStatic(method.getModifiers());
            if  (!isStatic) {
                fail(TARGET_METHOD_NAME + " メソッドは static として宣言されている必要があります。");
            }

            // assertion
            Object resultObj = method.invoke(null, 1, 2);
            assertEquals(0.5, (double)resultObj, 0.0001, 
                "divide(1, 2) の結果が 0.5 になっていません! （int同士の除算 a / b で整数切捨てになっていませんか？）"
            );

            resultObj = method.invoke(null, 15, 3);
            assertEquals(5.0, (double)resultObj, 0.0001, "divide(15, 3) の商の計算結果が間違っています!");

            resultObj = method.invoke(null, -7, 2);
            assertEquals(-3.5, (double)resultObj, 0.0001, "divide(-7, 2) の商の計算結果が間違っています!");

        } catch (NoSuchMethodException e){
            fail("メソッド " + TARGET_METHOD_NAME + "(int, int) が見つかりません。メソッド名や引数の型を確認してください。");
        } catch (Exception e) {
            fail("メソッド呼び出し中にエラーが発生しました。", e);
        }
    }

    @Test
    public void testDivideZero()
    {
        final String TARGET_METHOD_NAME = "divide";
        final Class<?>[] TARGET_METHOD_PARAMS = new Class<?>[]{int.class, int.class};
        
        try {
            Method method = Calculator.class.getDeclaredMethod(TARGET_METHOD_NAME, TARGET_METHOD_PARAMS);
            method.setAccessible(true);

            // check if static modifier defined
            boolean isStatic = Modifier.isStatic(method.getModifiers());
            if  (!isStatic) {
                fail(TARGET_METHOD_NAME + " メソッドは static として宣言されている必要があります。");
            }

            // assertion
            Object resultObj = method.invoke(null, 1, 0);
            assertEquals(0.0, (double)resultObj, 0.0001, "b が 0 の場合は 0 (0.0) を返すように条件分岐（if文）を追加してください!");

            resultObj = method.invoke(null, 26, 0);
            assertEquals(0.0, (double)resultObj, 0.0001, "b が 0 の場合は 0 (0.0) を返すように条件分岐（if文）を追加してください!");

        } catch (NoSuchMethodException e){
            fail("メソッド " + TARGET_METHOD_NAME + "(int, int) が見つかりません。メソッド名や引数の型を確認してください。");
        } catch (Exception e) {
            fail("メソッド呼び出し中にエラーが発生しました。", e);
        }
    }
}
