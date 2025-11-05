import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

// Nov11 2025
// supports Handling the Absence of the static Modifier and Non-public Declarations

public class CalculatorTest {

    @Test
    public void testTime()
    {
        final String TARGET_METHOD_NAME = "time";
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

            resultObj = method.invoke(null, 1, 2);
            actualResult = (int)resultObj;
            assertEquals(2,  actualResult, "積の計算結果が間違っています!");

        } catch (NoSuchMethodException e){
            fail("メソッド " + TARGET_METHOD_NAME + " が見つかりません。スペルミスを確認してください。");
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
            double actualResult = (double)resultObj;
            assertEquals(0.5, actualResult, 0.0001f, "商の計算結果が間違っています!");

            resultObj = method.invoke(null, 15, 3);
            actualResult = (double)resultObj;
            assertEquals(5.0, actualResult, 0.0001f, "商の計算結果が間違っています!");

        } catch (NoSuchMethodException e){
            fail("メソッド " + TARGET_METHOD_NAME + " が見つかりません。スペルミスを確認してください。");
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
            double actualResult = (double)resultObj;
            assertEquals(0, actualResult, 0.0001f, "商の計算結果が指示と異なります!");

            resultObj = method.invoke(null, 26, 0);
            actualResult = (double)resultObj;
            assertEquals(0, actualResult, 0.0001f, "商の計算結果が指示と異なります!");

        } catch (NoSuchMethodException e){
            fail("メソッド " + TARGET_METHOD_NAME + " が見つかりません。スペルミスを確認してください。");
        } catch (Exception e) {
            fail("メソッド呼び出し中にエラーが発生しました。", e);
        }
    }
}
