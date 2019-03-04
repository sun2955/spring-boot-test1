package com.sun.demo.base.utils;

public class Validator {
	
	public static boolean notEmpty(String s){
		return ! (s==null || s.isEmpty() || s.trim().isEmpty());
	}
	
	public static boolean notEmpty(Object s){
		if(s==null)
			return false;
		if(s instanceof String){
			if( ! notEmpty((String)s)){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 非空且全部是数字的字符串
	 */
	public static boolean numeric(String s){
		if(s==null || s.isEmpty())
			return false;
		for(int i=0,sz=s.length();i<sz;++i){
			char c = s.charAt(i);
			if(!(c>='0' && c<='9')){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 验证是否有效手机号码格式字符串
	 * @param s
	 * @return
	 */
	public static boolean phoneNumber(String s){
		if(s==null || s.isEmpty())
			return false;
		return numeric(s) && s.length()==11 && s.charAt(0)=='1';
	}
	
	/**
	 * 验证密码有效性
	 * @param s
	 * @return
	 */
	public static boolean password(String s){
		if(s==null)
			return false;
		int len = s.length();
		return len>=6 && len<=12;
	}
	
	/**
	 * 非空且是邮箱地址
	 */
	public static boolean email(String s){
		if(s==null || s.isEmpty() || s.length()>50)
			return false;
		int atIdx = -1024;
		int dotIdx = -1024;
		for(int i=0,sz=s.length();i<sz;++i){
			char c = s.charAt(i);
			if(Character.isWhitespace(c)){
				return false;
			}
			if(c=='@'){
				if(atIdx>0)
					return false;
				atIdx = i;
				if(atIdx==dotIdx+1)
					return false;
				if(i==0 || i==sz-1)
					return false;
			}
			if(c=='.'){
				if(i==atIdx+1)
					return false;
				if(i==0 || i==sz-1)
					return false;
				dotIdx=i;
			}
		}
		return atIdx+dotIdx>0;
	}
}
