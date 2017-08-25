package com.xbsafe.storm_wordCount;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;

public class WordCountTopology {
	public static void main(String[] args) throws Exception{
		TopologyBuilder builder = new  TopologyBuilder();
		builder.setSpout("spout", new RandomSentenceSpout(),3);
		builder.setBolt("split", new SplitSentence(),8).shuffleGrouping("spout");
		builder.setBolt("count",new WordCount(),8).fieldsGrouping("split", new Fields("word"));
		
		
		Config conf = new Config();
		conf.setDebug(true);
		
		if(args != null && args.length>0){
			conf.setNumAckers(3);
			StormSubmitter.submitTopology("WordCountTopology", conf, builder.createTopology());
		}else{
//			conf.setMaxTaskParallelism(3);
			LocalCluster cluster = new LocalCluster();
			cluster.submitTopology("WordCountTopology", conf, builder.createTopology());
			
			Thread.sleep(Integer.MAX_VALUE);
			cluster.shutdown();
		}
	}
}
