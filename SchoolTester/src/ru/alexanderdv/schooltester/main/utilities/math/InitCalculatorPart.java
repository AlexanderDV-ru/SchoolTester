package ru.alexanderdv.schooltester.main.utilities.math;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.9.5a
 */
@Deprecated
public class InitCalculatorPart
{
	public static InitCalculatorPart instance;
	public TextField area;
	public Button btnBackspace;
	public Button btnAA, btnAB, btnAC, btnAD, btnAE, btnAF, btnClear;
	public Button btnAH, btnAI, btnAJ, btnAK, btnAL, btnLeftBracket, btnRightBracket;
	public Button btnAO, btnAP, btnAQ, btnDivide, btnMultiply, btnPlus, btnMinus;
	public Button btnBA, btnBB, btnBC, btnRemainder, btn7, btn8, btn9;
	public Button btnBH, btnBI, btnBJ, btnFactorial, btn4, btn5, btn6;
	public Button btnBO, btnBP, btnBQ, btnPower, btn1, btn2, btn3;
	public Button btnPi, btnCB, btnCC, btnRoot, btn0, btnDot, btnEquals;

	@FXML
	private void initialize()
	{
		instance = this;
	}

}
