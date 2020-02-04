package org.ez.vk.dao_import_file_system;
public class Rynge
{
	static double x;
	//start
	private static double a = 0;
	// stop
	private static double b = 5;

	//
	public static double y1=Math.cos(x)/Math.pow(1 + Math.pow(Math.E, 2 * x),0.5);
	public static double y2=Math.sin(x)/Math.pow(1+Math.pow(Math.E,2*x),0.5);

	//   dy1/dx
	public static double derviY1(double x,double y10,double y20){

		return -y20+y10*(Math.pow(y10,2)+Math.pow(y20,2)-1);
	}

	//   dy2/dx
	public static  double derviY2(double x ,double y1,double y2)
	{
		return y1 + y2*(Math.pow(y1,2) + Math.pow(y2,2) - 1);
	}


	public static void main(String[] args) {
		int n = 10;
		double h = (b-a)/n;

		double k1,k2,k3,k4,m1,m2,m3,m4;
		double[] y10 = new double[n];//array of values y1
		double[] y20 = new double[n];//array of values y2
		y10[0] =1/Math.sqrt(2);
		y20[0] = 0;


		// Computation by 4th order Runge-Kutta


		//update x
		for (int i = 0; i < n-1; i++)
		{
			x = i * h;

			k1 = h * derviY1(x, y10[i], y20[i]);
			m1 = h * derviY2(x, y10[i], y20[i]);

			k2 = h * derviY1(x + h/2, y10[i] + k1/2, y20[i] + k1/2);
			m2 = h * derviY2(x + h/2, y10[i] + m1/2, y20[i] + m1/2);

			k3  = h * derviY1(x + h/2, y10[i] + k2/2, y20[i] + k2/2);
			m3  = h * derviY2(x + h / 2, y10[i] + m2 / 2, y20[i] + m2 / 2);

			k4 = h * derviY1(x + h, y10[i] + k3, y20[i] + k3);
			m4 = h * derviY2(x + h, y10[i] + m3, y20[i] + m3);

			y10[i+1] = y10[i] + h *(k1 + 2*k2 + 2*k3 + k4)/6;
			y20[i+1] = y20[i] +  h *(m1 + 2*m2 + 2*m3 + m4)/6;

			System.out.println("| " + x + " |" + " " + y10[i] + " " + "|" + " " + y20[i] + " " + "|");
		}
	}
}
