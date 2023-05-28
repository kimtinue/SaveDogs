package dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import logic.BuyItem;

public interface BuyItemMapper {

	@Insert("insert into buydetail (buy_no,seq,item_no,item_each) values(#{buy_no},#{seq},#{item_no},#{item_each})")
	void insert(BuyItem saleItem);

	@Select("select * from buydetail where buy_no =#{buy_no}")
	List<BuyItem> select(Map<String, Object> param);

}
