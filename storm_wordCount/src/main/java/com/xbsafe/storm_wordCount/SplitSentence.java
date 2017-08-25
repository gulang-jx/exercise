package com.xbsafe.storm_wordCount;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class SplitSentence extends BaseBasicBolt{

	public void execute(Tuple arg0, BasicOutputCollector arg1) {
		// TODO Auto-generated method stub
		String sentence = arg0.getString(0);
		for(String word:sentence.split(" ")){
			arg1.emit(new Values(word));
		}
	}

	public void declareOutputFields(OutputFieldsDeclarer arg0) {
		// TODO Auto-generated method stub
		arg0.declare(new Fields("word"));
	}

}
