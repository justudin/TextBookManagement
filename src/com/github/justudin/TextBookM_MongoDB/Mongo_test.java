package com.github.justudin.TextBookM_MongoDB;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;

public class Mongo_test {
	public static void main(String[] args) throws ClassNotFoundException {
		// Calculate execute times in ms
		long startTime = System.currentTimeMillis();

		// execute process
		mongo_insert_process();
		// mongo_read_process();

		long endTime = System.currentTimeMillis();
		System.out.println("Total execution time: " + (endTime - startTime)
				+ "ms");
	}

	public static void mongo_read_process() {
		try {

			// Connect database with the database server name and port number
			Mongo mongo = new Mongo("localhost", 27017);

			// open database
			DB db = mongo.getDB("textbook");

			DBCollection collection = db.getCollection("textbook");

			DBCursor cursor = collection.find(new BasicDBObject(),
					new BasicDBObject("_id", false)).limit(2000);
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}
			
			//System.out.println("Total data: " + a);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e);
		}
	}

	public static void mongo_insert_process() {
		try {
			Mongo mongo = new Mongo("localhost", 27017);

			DB db = mongo.getDB("textbook");

			DBCollection collection = db.getCollection("textbook");

			/*		
			BasicDBObject doc = new BasicDBObject();
			doc.put("ISBN", "9787561829318");
			doc.put("textbookName", "Mysql database Tutorial");
			doc.put("textbookName", "Andrew John");
			doc.put("textbookPub", "Tianjin University Press");
			collection.insert(doc);
			*/

			System.out.println("\nInsert the documents:");
			int a = 0;
			List<DBObject> list = new ArrayList<DBObject>();

			for (int i = 0; i < 20000; i++) {

				BasicDBObject data = new BasicDBObject();
				data.append("ISBN", "123456");
				data.append("textbookName", "Mysql database Tutorial");
				data.append("textbookEditor", "Andrew John");
				data.append("textbookPub", "Tianjin University Press");

				list.add(data);
				a++;
			}

			collection.insert(list);
			System.out.println("Total data: " + a);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e);
		}
	}
}