package com.example.utils;

import java.text.CollationKey;
import java.text.Collator;
import java.text.RuleBasedCollator;
import java.util.Comparator;

/**
 * @author Sodino E-mail:sodinoopen@hotmail.com
 * @version Time：2011-8-20 下午08:41:25
 */
public class ChinaAlphabetComparator implements Comparator<String> {

	private RuleBasedCollator collator;

	public ChinaAlphabetComparator() {
		collator = (RuleBasedCollator) Collator.getInstance(java.util.Locale.CHINA);
	}

	@Override
	public int compare(String obj1, String obj2) {
		obj1 = obj1.replace("重庆", "崇庆");
		obj2 = obj2.replace("重庆", "崇庆");
		CollationKey c1 = collator.getCollationKey(obj1);
		CollationKey c2 = collator.getCollationKey(obj2);
		// When sorting a list of Strings however, it is generally necessary to
		// compare each String multiple times. In this case, CollationKeys
		// provide better performance. The CollationKey class converts a String
		// to a series of bits that can be compared bitwise against other
		// CollationKeys. A CollationKey is created by a Collator object for a
		// given String.
		return c1.compareTo(c2);
		// return collator.compare(c1.getSourceString(), c2.getSourceString());
	}
}
