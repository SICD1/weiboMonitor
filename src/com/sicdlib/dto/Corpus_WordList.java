package com.sicdlib.dto;

//语料库分词列表
public class Corpus_WordList {
	
	private Integer cwl_id;				//语料库单词id
	private String cwl_word;			//语料库单词
	private Integer cwl_occurTimes;			//出现次数
	private Double cwl_occurFrequency;		//出现频率
	private Double cwl_accumFrequency;		//积累频率
	
	public Integer getCwl_id() {
		return cwl_id;
	}
	public String getCwl_word() {
		return cwl_word;
	}
	public Integer getCwl_occurTimes() {
		return cwl_occurTimes;
	}
	public Double getCwl_occurFrequency() {
		return cwl_occurFrequency;
	}
	public Double getCwl_accumFrequency() {
		return cwl_accumFrequency;
	}
	public void setCwl_id(Integer cwl_id) {
		this.cwl_id = cwl_id;
	}
	public void setCwl_word(String cwl_word) {
		this.cwl_word = cwl_word;
	}
	public void setCwl_occurTimes(Integer cwl_occurTimes) {
		this.cwl_occurTimes = cwl_occurTimes;
	}
	public void setCwl_occurFrequency(Double cwl_occurFrequency) {
		this.cwl_occurFrequency = cwl_occurFrequency;
	}
	public void setCwl_accumFrequency(Double cwl_accumFrequency) {
		this.cwl_accumFrequency = cwl_accumFrequency;
	}	
}
