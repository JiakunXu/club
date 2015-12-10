/**
 * 得到字符串的真实长度（双字节字符如汉字换算成两个单字节字符）.
 */
String.prototype.len = (function() {
	var re = /[^\x00-\xff]/g, xx = 'xx';

	return function() {
		return this.replace(re, xx).length;
	};
})();
String.prototype.getLength = String.prototype.len;

/**
 * 格式化字符串中的表达式：{\d}
 */
String.prototype.format = (function() {
	var re = /\{(?:\d+)\}/g;

	return function() {
		if (arguments.length == 0) {
			return this;
		}

		return this.replace(re, function(m, i) {
					return arguments[i] != null ? arguments[i] : m;
				});
	};
})();

/**
 * 去掉字符串前后空格.
 */
String.prototype.trim = (function() {
	var re = /^\s+|\s+$/g, s = '';

	return function() {
		return this.replace(re, s);
	};
})();

/**
 * 去掉字符串左边空格.
 */
String.prototype.trimLeft = (function() {
	var re = /^\s+/g, s = '';

	return function() {
		return this.replace(re, s);
	};
})();

/**
 * 去掉字符串右边空格.
 */
String.prototype.trimRight = (function() {
	var re = /\s+$/g, s = '';

	return function() {
		return this.replace(re, s);
	};
})();

/**
 * 去掉字符串中的html标签.
 */
String.prototype.stripHTML = (function() {
	var re = /<(?:.|\s)*?>/g, s = '';

	return function() {
		return this.replace(re, s);
	};
})();

/**
 * 格式化数字(#,###.##).
 * 
 * @param v
 * @param iScale
 *            精度位数
 * @param showPreScale
 *            是否添加默认的精度字符，比如金额数字，添加“.00”
 */
Number.format = function(v, iScale, showPreScale) {
	if (v == null || v == '') {
		return '';
	}

	if (showPreScale == null || typeof(showPreScale) != 'boolean') {
		showPreScale = true;
	}

	if (typeof(iScale) == 'number') {
		if (iScale < 0)
			iScale = 0;
		if (iScale > 8)
			iScale = 8;

		v = parseFloat(v).toFixed(iScale);
	} else {
		v = parseFloat(v).toFixed(2);
	}

	var arrs = String(v).split('.');

	var zs = arrs[0], // 整数部分
	xs = arrs[1], // 小数部分
	ret = [], c = 0;

	var i = zs.length - 1;
	for (; i >= 0; i--) {
		if (c++ >= 3) {
			c = 1;
			ret.push(',');
		}
		ret.push(zs.charAt(i));
	}

	ret.reverse();

	if (xs != null) {
		var tmp = parseFloat('0.' + xs);
		if (tmp != 0) {
			ret.push(String(tmp).substring(1));
		} else if (iScale != null && showPreScale) {
			ret.push(iScale < 2 ? '.0' : '.00');
		}
	}

	return ret.join('');
};

/**
 * 1. 取子字符串. 2. 子字符串 + ... + title提示
 * 
 * @param is
 *            开始位置（下标从0开始）
 * @param ie
 *            结束位置
 * @param showTitle
 *            是否显示title提示。true：显示、false：不显示。默认false
 */
String.prototype.substring2 = function(is, ie, showTitle) {
	if (arguments.length == 0) {
		return '';
	}

	if (arguments.length == 1) {
		ie = this.length;
	}

	if (typeof(is) != 'number' || typeof(ie) != 'number') {
		return '';
	}

	if (typeof(showTitle) == 'undefined' || typeof(showTitle) != 'boolean') {
		showTitle = false;
	}

	if (is < 0)
		is = 0;
	if (is > ie)
		is = ie;

	if (this.len() <= ie) {
		return this;
	}

	var sSource = this.substring(is);

	var str = '', c = '', len = 0;
	var reg = /[^\x00-\xff]/;

	for (var i = 0; c = sSource.charAt(i); i++) {
		str += c;
		len += (c.match(reg) != null ? 2 : 1);

		if (len >= ie)
			break;
	}

	if (showTitle) {
		return '<span title="' + this + '">' + str + '...<span>';
	}

	return str;
};

/**
 * dcmAdd
 * 
 * @param {}
 *            arg1
 * @param {}
 *            arg2
 * @return {}
 */
function dcmAdd(arg1, arg2) {
	var r1, r2, m;
	try {
		r1 = arg1.toString().split(".")[1].length
	} catch (e) {
		r1 = 0
	}
	try {
		r2 = arg2.toString().split(".")[1].length
	} catch (e) {
		r2 = 0
	}
	m = Math.pow(10, Math.max(r1, r2));
	return (dcmMul(arg1, m) + dcmMul(arg2, m)) / m;
}

/**
 * dcmSub
 * 
 * @param {}
 *            arg1
 * @param {}
 *            arg2
 * @return {}
 */
function dcmSub(arg1, arg2) {
	return dcmAdd(arg1, -arg2);
}

/**
 * dcmMul
 * 
 * @param {}
 *            arg1
 * @param {}
 *            arg2
 * @return {}
 */
function dcmMul(arg1, arg2) {
	var m = 0, s1 = arg1.toString(), s2 = arg2.toString();
	try {
		m += s1.split(".")[1].length
	} catch (e) {
	}
	try {
		m += s2.split(".")[1].length
	} catch (e) {
	}
	return Number(s1.replace(".", "")) * Number(s2.replace(".", ""))
			/ Math.pow(10, m);
}

/**
 * dcmDiv
 * 
 * @param {}
 *            arg1
 * @param {}
 *            arg2
 * @return {}
 */
function dcmDiv(arg1, arg2) {
	return dcmMul(arg1, 1 / arg2);
}

/**
 * round
 * 
 * @param {}
 *            a
 * @param {}
 *            b
 * @return {}
 */
function round(a, b) {
	return (Math.round(a * Math.pow(10, b)) * Math.pow(10, -b));
}
