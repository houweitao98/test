/**
 * 2016年9月10日 上午11:33:43
 * @auto Jack.Hou
 * @Copyright 1999-2020 http://www.yihecloud.com/ Croporation Limited.
 */
package com.yihecloud.jack.jack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * @author Administrator
 *
 *         Calculate.java
 */
public class Calculate {

	public String getCal(String path, String pwd) {

		// ZipUtil.unzip(path, path, pwd);

		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(path));// 新建缓存区读取为所需统计文件的读取

			StringBuffer mp = new StringBuffer();// 更新字符缓存为mp
			String line;// 把要统计的文件装入字符串m
			while ((line = br.readLine()) != null) {
				mp.append(line);// 设置字符缓存的搜索路径是字符串m
			}
			Map<String, Integer> map = new HashMap<String, Integer>();// 运用哈希排序的方法进行排序
			StringTokenizer st = new StringTokenizer(mp.toString(), ";");// 分割字符串
			// 用来测试是否有此标记生成器的字符串可以有更多的标记。并把分割好的单词保存在letter字符串中。
			while (st.hasMoreTokens()) {
				String letter = st.nextToken();
				int count;
				if (map.get(letter) == null) {
					count = 1;// 表明了没有进行分割。
				} else {
					count = map.get(letter).intValue() + 1;
				}
				map.put(letter, count);
			}
			Set<WordEntity> set = new TreeSet<WordEntity>();
			for (String key : map.keySet()) {
				set.add(new WordEntity(key, map.get(key)));
			}

			System.out.println("频率排名前5的单词是：");
			int count = 1;
			for (Iterator<WordEntity> it = set.iterator(); it.hasNext();) {
				WordEntity w = it.next();
				System.out.println("第" + count + "名为单词:" + w.getKey() + " 出现的次数为： " + w.getCount());
				if (count == 5)
					break;
				count++;
			}
		} catch (Exception e) {
			System.out.println("文件未找到~！");// 异常处理
		}

		return "";
	}
}

class WordEntity implements Comparable<WordEntity> {
	private String key;
	private Integer count;

	public WordEntity(String key, Integer count) {
		this.key = key;
		this.count = count;
	}

	public int compareTo(WordEntity o) {
		int cmp = count.intValue() - o.count.intValue();
		return (cmp == 0 ? key.compareTo(o.key) : -cmp);
		// 只需在这儿加一个负号就可以决定是升序还是降序排列 -cmp降序排列，cmp升序排列
		// 因为TreeSet会调用WorkForMap的compareTo方法来决定自己的排序
	}

	public String toString() {
		return key + " 出现的次数为：" + count;
	}

	public String getKey() {
		return key;
	}

	public Integer getCount() {
		return count;
	}
}
