import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.*;

/**
 * @version (20220522) supporting both println and print("\n") on Windows
 * @version (20261014) revised for partial scoring, index safety, and zero divisor handling
 **/
public class Prog61Test {
    InputStream originalIn;
    PrintStream originalOut;
    ByteArrayOutputStream bos;
    StandardInputStream in;

    @BeforeEach
    void before() {
        //back up binding
        originalIn  = System.in;
        originalOut = System.out;
        //modify binding
        bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
        
        in = new StandardInputStream();
        System.setIn(in);
    }
    
    @AfterEach
    void after() {
       System.setOut(originalOut);
       System.setIn(originalIn);
    }

    @Test
    public void testMainCalculation72_66() {
        Prog61.main(new String[]{"72", "66"});

        String output = bos.toString().replace("\r\n", "\n");
        String[] lines = output.split("\n");

        // 1. 行数チェック
        assertTrue(lines.length >= 4, 
            "実行結果が4行分確認できません! メッセージ出力の直後に改行（println）を入れているか確認してください。"
        );

        // 2. メッセージと計算結果の検証 (72 × 66 = 4752 / 72 ÷ 66 ≒ 1.09091)
        assertTrue(lines[0].contains("積を計算します"), "1行目に「積を計算します」が出力されていません。");
        assertTrue(lines[1].contains("4752"), 
            "2行目に 72 × 66 の計算結果（4752）が正しく出力されていないか、出力位置が異なります。"
        );

        assertTrue(lines[2].contains("商を計算します"), "3行目に「商を計算します」が出力されていません。");
        try {
            double actualDivide = Double.parseDouble(lines[3].trim());
            assertEquals(1.09091, actualDivide, 0.001, 
                "4行目に 72 ÷ 66 の計算結果（1.09091...）が正しく出力されていません。Calculator.divide() を正しく呼び出しているか確認してください。"
            );
        } catch (NumberFormatException e) {
            fail("4行目の計算結果を数値（double）として読み込めませんでした。");
        }
    }

    /**
     * ゼロ除算時の main メソッド動作検証 (引数: 10, 0)
     */
    @Test
    public void testMainZeroDivisor() {
        Prog61.main(new String[]{"10", "0"});

        String[] lines = bos.toString().replace("\r\n", "\n").split("\n");
        assertTrue(lines.length >= 4, "実行結果の行数が不足しています。");

        // 積の検証 (10 * 0 = 0)
        assertTrue(lines[1].contains("0"), "10 × 0 の結果（0）が出力されていません。");

        // 商の検証 (10 / 0 -> 0 または 0.0)
        try {
            double actualDivide = Double.parseDouble(lines[3].trim());
            assertEquals(0.0, actualDivide, 0.0001, 
                "第2引数が 0 の場合、商の出力結果は 0 (0.0) になる必要があります。"
            );
        } catch (NumberFormatException e) {
            fail("4行目の計算結果を数値（double）として読み込めませんでした。");
        }
    }
}
