package com.nghiabui.kommon.xml;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class NodeUtil {

	public static List<Element> toList(NodeList nodes) {
		final List<Element> list = new ArrayList<>();
		for (int i = 0; i < nodes.getLength(); i++) {
			final Node node = nodes.item(i);
			if (isElement(node)) {
				list.add((Element) node);
			}
		}
		return list;
	}
	
	public static boolean isElement(Node node) {
		return node.getNodeType() == Node.ELEMENT_NODE;
	}

}
