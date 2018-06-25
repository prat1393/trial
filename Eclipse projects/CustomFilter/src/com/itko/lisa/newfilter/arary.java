package com.itko.lisa.newfilter;

public class arary {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] a=new int[10];
		a[0]=1;
		a[1]=2;
		a[2]=3;a[4]=5;
	System.out.println(a.length);
	int c=a.length;
		int []b=new int[10];
		
		for(int i=0;i<=c;i++)
		{
			b[i]=a[c-i];
		}
System.out.println(b);
	}

}
