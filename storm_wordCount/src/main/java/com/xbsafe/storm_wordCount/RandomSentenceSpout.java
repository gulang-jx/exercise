package com.xbsafe.storm_wordCount;

import java.util.Map;
import java.util.Random;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;

public class RandomSentenceSpout extends BaseRichSpout{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SpoutOutputCollector collector;
	Random rand;
	public void nextTuple() {
		Utils.sleep(100);
		String[] sentences = {
				"aaaaaaaaaaaaaaaaa",
				"bbbbbbbbbbbbbb",
				"cccccccccc"
		};
		String sentence = sentences[rand.nextInt(sentences.length)];
		collector.emit(new Values(sentence));
	}

	public void open(Map arg0, TopologyContext arg1, SpoutOutputCollector arg2) {
		// TODO Auto-generated method stub
		collector =arg2;
		rand = new Random();
	}

	public void declareOutputFields(OutputFieldsDeclarer arg0) {
		// TODO Auto-generated method stub
		arg0.declare(new Fields("word"));
	}

}
