package com.xbsafe.storm_wordCount;

import java.util.HashMap;
import java.util.Map;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class WordCount extends BaseBasicBolt{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4182166000925260826L;
	Map<String,Integer> map  = new HashMap<String, Integer>();
	public void execute(Tuple arg0, BasicOutputCollector arg1) {
		// TODO Auto-generated method stub
		String sentence = arg0.getString(0);
		for(String word:sentence.split(" ")){
			Integer count = map.get(word);
			if(count == null) count=0;
			count ++;
			map.put(word, count);
			arg1.emit(new Values(word,count));
		}
	}

	@Override
	public void cleanup() {
		map.forEach((k,v)->{
			System.out.println("k="+k+"=====v="+v);
		});
	}

	public void declareOutputFields(OutputFieldsDeclarer arg0) {
		// TODO Auto-generated method stub
		arg0.declare(new Fields("word","count"));
	}

}
