package alg.demo3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class TspReader {
	
	public static TspProblem readTSP(String filename, int numCities) throws IOException {
		int[] x;
		int[] y;
		String strbuff;
		BufferedReader data = new BufferedReader(new InputStreamReader(
				new FileInputStream(filename)));
		int[][] dist = new int[numCities][numCities];
		x = new int[numCities];
		y = new int[numCities];
		while ((strbuff = data.readLine())!=null) {
			if (!Character.isAlphabetic(strbuff.charAt(0)))
				break;
		}
		String[] tmp = strbuff.split(" ");
		x[0] = Integer.valueOf(tmp[1]);//
		y[0] = Integer.valueOf(tmp[2]);// y���� x����
		
		for (int i = 1; i < numCities; i++) {
			// ��ȡһ�����ݣ����ݸ�ʽ1 6734 1453
			strbuff = data.readLine();
			// �ַ��ָ�
			String[] strcol = strbuff.split(" ");
			x[i] = Integer.valueOf(strcol[1]);// x����
			y[i] = Integer.valueOf(strcol[2]);// y����
		}
		data.close();
		TspProblem problem = new TspProblem(x,y);
		return problem;
	}
	

	public static void main(String[] args) throws IOException {
		TspProblem problem = TspReader.readTSP("dcm-domain/resources/eil23.txt", 23);
		int[] x = problem.getxCoors();
		for (int i=0;i<x.length;i++) {
			System.out.println(x[i]);
		}
	}
}
