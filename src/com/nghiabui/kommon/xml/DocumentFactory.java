package com.nghiabui.kommon.xml;

import com.nghiabui.kommon.DebugException;
import com.nghiabui.kommon.Path;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.Optional;

public class DocumentFactory {

	private static final DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	private static DocumentBuilder dBuilder = null;

	public static Optional<Document> createDocument(Path path) {
		if (dBuilder == null) {
			try {
				dBuilder = dbFactory.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				throw new DebugException(e);
			}
		}
		final Document document;
		try {
			document = dBuilder.parse(path.value());
		} catch (Exception e) {
			return Optional.empty();
		}
		document.getDocumentElement().normalize();
		return Optional.of(document);
	}

}
