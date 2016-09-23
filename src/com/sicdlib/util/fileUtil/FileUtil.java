package com.sicdlib.util.fileUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.sicdlib.dto.Corpus_Essays;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class FileUtil {

	public static List<Corpus_Essays> getFileUtil(String filepath){

		File file = new File(filepath);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				System.out.println(tempString);

			}
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static List<Corpus_Essays> writeFileToDB(String filepath){
		File in = new File(filepath);
		Document doc = null;
		List<Corpus_Essays> essays = new LinkedList<Corpus_Essays>();
		try {
			doc = Jsoup.parse(in, "utf8", "");
			Elements documents = doc.select("doc");
			for (Element document : documents) {
				Corpus_Essays essay = new Corpus_Essays();
				String title = document.children().select("contenttitle").text();
				String content = document.children().select("content").text();
				essay.setCe_title(title);
				essay.setCe_content(content);
				essays.add(essay);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return essays;
	}

	public static void main(String[] args) {
		try {
			String filepath = "news.dat";
			writeFileToDB(filepath);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
