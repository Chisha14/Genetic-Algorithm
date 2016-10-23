package com.gene.algorithm;

import java.util.Random;

public class algorithmGene {
	
	static int time[][] = new int[3][48];
	static int days[][] = new int[3][48];
	static int cls[][] = new int[3][48];
	static int drs[][] = new int[3][48];
	static double fit1 = 0;
	static double fit2 = 0;
	static double fit3 = 0;
	static 	boolean isFit1 = false , isFit2 = false, isFit3 = false;
	static 	boolean win1 = false , win2 = false, win3 = false;	
	static int count = 0;

	
	static Random rand = new Random();
	public static void main(String[] args) {
		
		for(int pop = 0 ; pop < 3 ; pop++){
			if(pop == 1)
				count = 0;
			if(pop == 2)
				count = 0;
			for(int i = 0 ; i < 48 ; i++)
			{
				int randtime = rand.nextInt((4-1) + 1) + 1;
				int randday = rand.nextInt((3-1) + 1) + 1;
				int randcls = rand.nextInt((4-1) + 1) + 1;
				int randdr = rand.nextInt((4-1) + 1) + 1;
				time[pop][i] = randtime;
				days[pop][i] = randday;
				cls[pop][i] = randcls;
				drs[pop][i] = randdr;
				//System.out.println("\tComb: " + i + "\nDays: " + days[pop][i] + "\nTime: " + time[pop][i] + "\nClass: " + cls[pop][i] + "\nDr.: " + drs[pop][i] + "\n-----------------------");
				count++;
			}
		}//pop ends
		count = 0;
		CalcFitness();
		
		System.out.println("Choosing best parents ....");
		
		if(fit1 >= fit2 && fit1 >= fit3){
			//population 1 is the best;
			isFit1 = true;
			if(fit2 >= fit3)
				//pop 2 is better than 3;
				isFit2 = true;
			else
				//pop 3 is better than 2;
				isFit3 = true;
		}
			
		else if(fit2 >= fit1 && fit2 >= fit3){
			//population 2 is the best;
			isFit2 = true;
			if(fit1 >= fit3)
				//pop 1 is better than 3;
				isFit1 = true;
			else
				//pop 3 is better than 1;
				isFit3 = true;
		}
			
		else if(fit3 >= fit1 && fit3 >= fit2){
			//population 3 is the best;
			isFit3 = true;
			if(fit1 >= fit2)
				//pop 1 is better than 2;
				isFit1 = true;
			else
				//pop 2 is better than 1;
				isFit2 = true;
		}
		System.out.print("Populations ");
		if(isFit1)
			System.out.print("1, ");
		if(isFit2)
			System.out.print("2, ");
		if(isFit3)
			System.out.print("3 ");
		System.out.print("are the best...");
		
		//crossover and mutation
		while(true){
			try {
				Thread.sleep(2000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			crossOver();
			if(fit1 >= 100){
				win1 = true;
				break;
			}
			if(fit2 >= 100){
				win2 = true;
				break;
			}
			if(fit3 >= 100){
				win3 = true;
				break;
			}
		}
		
		if(win1){
			
			for(int i = 0 ; i < 48 ; i++)
				System.out.println("\tComb: " + i + "\nDays: " + days[0][i] + "\nTime: " + time[0][i] + "\nClass: " + cls[0][i] + "\nDr.: " + drs[0][i] + "\n-----------------------");
		}
			
		else if(win2){
			for(int i = 0 ; i < 48 ; i++)
				System.out.println("\tComb: " + i + "\nDays: " + days[1][i] + "\nTime: " + time[1][i] + "\nClass: " + cls[1][i] + "\nDr.: " + drs[1][i] + "\n-----------------------");
	
		}
		else if(win3){
			for(int i = 0 ; i < 48 ; i++)
				System.out.println("\tComb: " + i + "\nDays: " + days[2][i] + "\nTime: " + time[2][i] + "\nClass: " + cls[2][i] + "\nDr.: " + drs[2][i] + "\n-----------------------");
	
		}
	}	

	private static void crossOver(){
		int c = 0;
		for(int pop = 0 ; pop < 3 ; pop++)
		{

			if(isFit1 && isFit2){
				if(pop == 2)
					break;
				if(pop == 0)
					c = 0;
				else
					c = 23;
				for(int i = 0 ; i < 24 ; i++){
					
					days[0][c] = days[pop][i];
					time[0][c] = time[pop][i];
					cls[0][c] = cls[pop][i];
					drs[0][c++] = drs[pop][i];
					
				}
				if(pop == 0)
					c = 0;
				else
					c = 23;
				for(int i = 24 ; i < 48 ; i++){
					
					days[1][c] = days[pop][i];
					time[1][c] = time[pop][i];
					cls[1][c] = cls[pop][i];
					drs[1][c++] = drs[pop][i];
					//System.out.println("\tComb: " + c + "\nDays: " + days[pop][c] + "\nTime: " + time[pop][c] + "\nClass: " + cls[pop][c] + "\nDr.: " + drs[pop][c] + "\n-----------------------");
				}
			}
			else if(isFit1 && isFit3){
				
				if(pop == 1)
					pop = 2;
				if(pop == 0)
					c = 0;
				else
					c = 23;
				for(int i = 0 ; i < 24 ; i++){
					days[0][c] = days[pop][i];
					time[0][c] = time[pop][i];
					cls[0][c] = cls[pop][i];
					drs[0][c++] = drs[pop][i];
					//System.out.println("\tComb: " + c + "\nDays: " + days[pop][c] + "\nTime: " + time[pop][c] + "\nClass: " + cls[pop][c] + "\nDr.: " + drs[pop][c] + "\n-----------------------");
				}
				if(pop == 0)
					c = 0;
				else
					c = 23;
				for(int i = 24 ; i < 48 ; i++){
					
					days[2][c] = days[pop][i];
					time[2][c] = time[pop][i];
					cls[2][c] = cls[pop][i];
					drs[2][c++] = drs[pop][i];
					//System.out.println("\tComb: " + c + "\nDays: " + days[pop][c] + "\nTime: " + time[pop][c] + "\nClass: " + cls[pop][c] + "\nDr.: " + drs[pop][c] + "\n-----------------------");
				}
			}
			else{
				
				if(pop == 0)
					pop = 1;
				if(pop == 0)
					c = 0;
				else
					c = 23;
				for(int i = 0 ; i < 24 ; i++){
					days[0][c] = days[pop][i];
					time[0][c] = time[pop][i];
					cls[0][c] = cls[pop][i];
					drs[0][c++] = drs[pop][i];
					//System.out.println("\tComb: " + c + "\nDays: " + days[pop][c] + "\nTime: " + time[pop][c] + "\nClass: " + cls[pop][c] + "\nDr.: " + drs[pop][c] + "\n-----------------------");
				}
				if(pop == 0)
					c = 0;
				else
					c = 23;
				for(int i = 24 ; i < 48 ; i++){
					
					days[2][c] = days[pop][i];
					time[2][c] = time[pop][i];
					cls[2][c] = cls[pop][i];
					drs[2][c++] = drs[pop][i];
					//System.out.println("\tComb: " + c + "\nDays: " + days[pop][c] + "\nTime: " + time[pop][c] + "\nClass: " + cls[pop][c] + "\nDr.: " + drs[pop][c] + "\n-----------------------");
				}
			}
		}//end all for

		count = 0;
		CalcFitness();
		
		
	}
	
	private static void CalcFitness(){
		fit1 = 0;
		fit2 = 0;
		fit3 = 0;
		for(int pop = 0 ; pop < 3 ; pop++){

			if(pop == 0){
				System.out.println("Population 1: ");
			}
			else if(pop == 1){
				fit1 = ((48 - count) * 1.0 / 48) * 100;
				System.out.println("Fitness= " + count);
				count = 0;
				System.out.println("Population 2: ");
			}
			else{
				fit2 = ((48 - count) * 1.0 / 48) * 100;
				System.out.println("Fitness= " + count);
				count = 0;
				System.out.println("Population 3: ");
			}
			for(int i = 0 ; i < 48 ; i++)
			{
				for(int j = i ; j < 48 ; j++)
				{
					if(pop == 0 && i != j && days[pop][i] == days[pop][j] && time[pop][i] == time[pop][j] && cls[pop][i] == cls[pop][j]){
						
						int randtime = rand.nextInt((4-1) + 1) + 1;
						int randday = rand.nextInt((3-1) + 1) + 1;
						int randcls = rand.nextInt((4-1) + 1) + 1;
						int randdr = rand.nextInt((4-1) + 1) + 1;
						time[pop][i] = randtime;
						days[pop][i] = randday;
						cls[pop][i] = randcls;
						drs[pop][i] = randdr;
						System.out.println("\tComb: " + i + "\nDays: " + days[pop][i] + "\nTime: " + time[pop][i] + "\nClass: " + cls[pop][i] + "\nDr.: " + drs[pop][i] + "\n-----------------------");
						count ++;
						
					}
					if(pop == 1 && i != j && days[pop][i] == days[pop][j] && time[pop][i] == time[pop][j] && cls[pop][i] == cls[pop][j]){
						int randtime = rand.nextInt((4-1) + 1) + 1;
						int randday = rand.nextInt((3-1) + 1) + 1;
						int randcls = rand.nextInt((4-1) + 1) + 1;
						int randdr = rand.nextInt((4-1) + 1) + 1;
						time[pop][i] = randtime;
						days[pop][i] = randday;
						cls[pop][i] = randcls;
						drs[pop][i] = randdr;
						System.out.println("\tComb: " + i + "\nDays: " + days[pop][i] + "\nTime: " + time[pop][i] + "\nClass: " + cls[pop][i] + "\nDr.: " + drs[pop][i] + "\n-----------------------");
						count ++;
					}
					if(pop == 2 && i != j && days[pop][i] == days[pop][j] && time[pop][i] == time[pop][j] && cls[pop][i] == cls[pop][j]){
						int randtime = rand.nextInt((4-1) + 1) + 1;
						int randday = rand.nextInt((3-1) + 1) + 1;
						int randcls = rand.nextInt((4-1) + 1) + 1;
						int randdr = rand.nextInt((4-1) + 1) + 1;
						time[pop][i] = randtime;
						days[pop][i] = randday;
						cls[pop][i] = randcls;
						drs[pop][i] = randdr;
						System.out.println("\tComb: " + i + "\nDays: " + days[pop][i] + "\nTime: " + time[pop][i] + "\nClass: " + cls[pop][i] + "\nDr.: " + drs[pop][i] + "\n-----------------------");
						count ++;
					}
				}
				
			}
		}//pop ends
		fit3 = ((48 - count) * 1.0 / 48) * 100;
		System.out.println("Fitnesses= " + fit1 + "\t" + fit2 + "\t" + fit3);
		
	}
}
