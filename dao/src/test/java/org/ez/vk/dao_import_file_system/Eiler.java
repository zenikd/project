package org.ez.vk.dao_import_file_system;

import java.io.PrintWriter;

public class Eiler
{
	public static void main(String[] args)
	{

		PrintWriter printWriter = new PrintWriter(System.out);

		final double a = 0;
		final double b = 1;

		final double y0 = 0;


		final int n = 10;
		final double h = (b - a) / n;

		double[] x = new double[n + 1];
		double[] y = new double[n + 1];


		x[0] = a;
		y[0] = y0;


		for (int i = 1; i <= n; i++)
		{
			x[i] = x[i - 1] + h;
			y[i] = y[i - 1] + h * calculateF(x[i - 1], y[i - 1]);
		}
		for (int i = 0; i <= n; i++)
		{
			double error = Math.abs(y[i] - calculateY(x[i]));
			printWriter.printf("%.8G\t%.8G\t%.8G\t%.8G\n", x[i], calculateY(x[i]), y[i], error);
		}

		printWriter.close();
	}

	static double calculateY(double x)
	{
		return Math.exp(x) * Math.sin(x);
	}

	static double calculateF(double x, double y)
	{
		return y + Math.exp(x) * Math.cos(x);
	}
}