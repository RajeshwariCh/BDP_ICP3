package com.matrix;



	import org.apache.hadoop.conf.*;
	import org.apache.hadoop.fs.Path;
	import org.apache.hadoop.io.*;
	import org.apache.hadoop.mapreduce.*;
	import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
	import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
	import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
	import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

	public class Multiplication {
		
	    public static void main(String[] args) throws Exception {
	    	if (args.length != 2) {
	            System.err.println("Usage: MatrixMultiply <in_dir> <out_dir>");
	            System.exit(2);
	        }
	    	Configuration conf = new Configuration();
	        // M is an m-by-n matrix; N is an n-by-p matrix.
	        conf.set("m", "3");
	        conf.set("n", "3");
	        conf.set("p", "3");
	        @SuppressWarnings("deprecation")
			Job job = new Job(conf, "MatrixMultiply");
	        job.setJarByClass(Multiplication.class);
	        job.setOutputKeyClass(Text.class);
	        job.setOutputValueClass(Text.class);
	 
	        job.setMapperClass(MatrixMap.class);
	        job.setReducerClass(MatrixReduce.class);
	 
	        job.setInputFormatClass(TextInputFormat.class);
	        job.setOutputFormatClass(TextOutputFormat.class);
	 
	        FileInputFormat.addInputPath(job, new Path(args[0]));
	        FileOutputFormat.setOutputPath(job, new Path(args[1]));
	 
	        job.waitForCompletion(true);
	    }
	}
