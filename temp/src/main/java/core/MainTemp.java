package core;

import java.util.stream.StreamSupport;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Splitter;

/**
 * @author chengyunfei
 * @version 1.0
 * @date : 2017-07-03 17:13:39
 * @Description ch ...
 */
public class MainTemp {
	public static void main(String[] args) {
		String ids = ",1,,2,3,4,5,";
		Iterable<String> idList = Splitter.on(",").omitEmptyStrings().trimResults().split(ids);
		Long[] idArray = StreamSupport.stream(idList.spliterator(), false).map(Long::valueOf).toArray(Long[]::new);

		for (Long id : idArray) {
			System.out.println(id);
		}
		
		
		String idString = StringUtils.join(idArray, ",");
		
		System.out.println(idString);
	}

}
