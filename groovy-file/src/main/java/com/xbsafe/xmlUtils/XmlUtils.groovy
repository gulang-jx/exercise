package com.xbsafe.xmlUtils

class XmlUtils {
public static void main(def args){
		def user = new XmlParser().parse("source/test.xml");
		user.children().each{u->
			println "user:"+u.name();
			u.children().each{p->
				println "\tname:"+p.name()+";sex:"+p.sex.text()+";age:"+p.age.text();
			}
			println "";
		}
	}
}
