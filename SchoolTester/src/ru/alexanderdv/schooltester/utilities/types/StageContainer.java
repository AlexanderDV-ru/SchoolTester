package ru.alexanderdv.schooltester.utilities.types;

import java.util.Random;

import javafx.stage.Stage;

/**
 * 
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.9.0a
 */
public class StageContainer
{
	private static final Random random = new Random();
	protected Stage stage;

	public StageContainer(Stage stage)
	{
		this.stage = stage;
	}

	public Stage getStage(Wrapper wrapper)
	{
		return wrapper.getStage(stage);
	}

	/**
	 * 
	 * 
	 * @author AlexanderDV/AlexandrDV
	 * @version 5.9.0a
	 */
	public static abstract class Wrapper
	{
		private boolean used;

		public Wrapper()
		{
			used = false;
		}

		public final Stage getStage(Stage stage)
		{
			String rand = "" + (random.nextInt(9) + 1) + "" + random.nextInt(10) + "" + random.nextInt(10) + "" + random.nextInt(10) + "" + random.nextInt(10);
			if (used)
				throw new RuntimeException("Wrapper already used!");
			char[] r = check(Integer.parseInt(rand)), m = rand.toCharArray();
			if (r[0] != m[0] || r[1] != m[1] || r[2] != m[2] || r[3] != m[3] || r[4] != m[4])
				throw new RuntimeException("Wrapper is wrong!");
			used = true;
			return stage;
		}

		public abstract char[] check(int algo);
	}
}
