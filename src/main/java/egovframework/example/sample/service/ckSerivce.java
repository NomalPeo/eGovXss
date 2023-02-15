package egovframework.example.sample.service;

import java.util.List;

import egovframework.vo.ckVO;

public interface ckSerivce {

	public String insert(ckVO vo);

	public List getList(ckVO vo);

	public ckVO getContent(ckVO vo);

	public int editCon(ckVO vo);
	
	

}
