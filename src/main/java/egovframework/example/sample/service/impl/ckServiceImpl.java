package egovframework.example.sample.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.example.sample.service.ckSerivce;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.vo.ckVO;
@Service("ckService")
public class ckServiceImpl extends EgovAbstractServiceImpl implements ckSerivce {

		
	@Resource(name="ckDAO")
	private ckDAO ckDao;
	
	@Override
	public String insert(ckVO vo) {
		return ckDao.insert(vo);
	}

	@Override
	public List getList(ckVO vo) {
		return ckDao.getListAll(vo);
	}

	@Override
	public ckVO getContent(ckVO vo) {
		return ckDao.getContent(vo);
	}

	@Override
	public int editCon(ckVO vo) {
		return ckDao.eidtCon(vo);
	}

}
