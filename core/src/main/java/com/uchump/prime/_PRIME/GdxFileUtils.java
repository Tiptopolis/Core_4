package com.uchump.prime._PRIME;


import static com.uchump.prime._PRIME.uAppUtils.*;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class GdxFileUtils {

	// listDirectory
	public static String listDirectory(String dir) {
		//

		String log = "";
		log += "\n" + "DirectoryList";
		log += "\n (((";
		log += "FileSys: -GDXFileUtils ";
		log += "\n";
		log += "Directory: " + dir;

		FileHandle input = Gdx.files.internal("." + dir); // tmp directory for created assets?
		log += "\n";
		log += "FilesFound: " + input.list().length;
		log += "\n";
		log += "\n" + input.type();
		log += "\n";

		for (FileHandle f : input.list()) {

			log += "^>";
			log += "\n" + f.type();
			log += "><" + f.path();
			log += "\n";
			log += ">><<" + f.list();
			log += "\n";
		}

		log += "\n::___::";
		log += "\n";
		return log;
	}

	public static ArrayList<FileHandle> getFilesFrom(String dir) {
		FileHandle input = Gdx.files.internal("." + dir);
		ArrayList<FileHandle> result = new ArrayList<FileHandle>();
		for (FileHandle f : input.list()) {
			result.add(f);
		}

		return result;
	}
	
	public static FileHandle getFile(String dir) {
		FileHandle input = Gdx.files.internal("" + dir);
		

		return input;
	}

	public static ArrayList<FileHandle> fillFilesFrom(ArrayList<FileHandle> to, String dir) {
		FileHandle input = Gdx.files.internal("." + dir);
		ArrayList<FileHandle> result = new ArrayList<FileHandle>();
		for (FileHandle f : input.list()) {
			result.add(f);
		}

		for (FileHandle h : result) {
			to.add(h);
		}

		return to;
	}

}
