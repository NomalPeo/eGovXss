package egovframework.example.sample.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.vo.ckVO;

@Repository("ckDAO")
public class ckDAO extends EgovAbstractDAO {

	public String insert(ckVO vo) {
		return (String)insert("ckInsert", vo);
	}

	public List getListAll(ckVO vo) {
		return list("ckSelectAll",vo);
	}

	public ckVO getContent(ckVO vo) {
		return (ckVO) select("ckGetContnet",vo);
	}

	public int eidtCon(ckVO vo) {
		return update("ckEdit", vo);
	}

}
