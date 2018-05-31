package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TreeMap;

import javax.swing.text.MaskFormatter;

public class Utils {
	
	/**
	 * Retorna o arquivo com os dados serializados
	 * @param caminho
	 * @return file /empresa.txt /cliente.txt
	 */
	public File retornarArquivo(String caminho){
		URL url = ClassLoader.getSystemClassLoader().getResource(caminho);
		String caminhoArquivo = url.getFile();
		File f = new File(caminhoArquivo);
		return f;
	}
	
	/**
	* GravarNoArquivo
	 * @param TreeMap<Integer, EmpresaVO>
	 * @param File
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void gravarNoArquivo(TreeMap tm, File f)
			throws FileNotFoundException, IOException {
		FileOutputStream fos = new FileOutputStream(f);
		ObjectOutputStream obs = new ObjectOutputStream(fos);
		obs.writeObject(tm);
		obs.flush();
		obs.close();		
	}

	
	/**
	 * Retorna chave que sera o idEmpresa ou IdCliente
	 * @param TreeMap<Integer, EmpresaVO>
	 * @return chave idEmpresa / IdCliente
	 */
	public Integer criarChave(TreeMap tm) {
		Integer i = null;
		if(tm.size() == 0){
			i = 1;
		}else{
			i = (Integer)tm.lastKey() + 1;
		}
		return i;
	}
	
	/**
	 * RetornarTreMap <integer, EmpresaVO> ou RetornarTreMap <integer, ClienteVO>
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public TreeMap retornarTreMap(File f) throws FileNotFoundException , IOException, ClassNotFoundException{
		TreeMap tm = new TreeMap();
		FileInputStream fis = new FileInputStream(f);
		ObjectInputStream ois = new ObjectInputStream(fis);
		tm = (TreeMap) ois.readObject();
		return tm;
	}

	/**
	 * RetornaMascaraTelefone
	 * @return mascaraTelefone
	 */
	public MaskFormatter getMascaraTelefone(){
		MaskFormatter maskaraTel = null;
		try {
			maskaraTel = new MaskFormatter("(##)####-####");
			maskaraTel.setPlaceholderCharacter('_');
		} catch (ParseException ecx) {
			ecx.printStackTrace();
		}
		return maskaraTel;		
	}
	
	public String retornarData(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String strData = sdf.format(Calendar.getInstance().getTime());
		return strData;
	}
	
	public String retornarTelLimpo(String tel) {
		String telLimpo = tel;
		telLimpo = telLimpo.replace("-", "");
		telLimpo = telLimpo.replace("_", "");
		telLimpo = telLimpo.replace("(", "");
		telLimpo = telLimpo.replace(")", "");
		if (telLimpo.trim().equals("")) {
			return "";
		} else {
			return tel;
		}
	}	
	
	
	public String retornaDataLimpa(String data){
		data = data.replace("/", "");
		return data;
	}
	
	/**
	 * retorna mes
	 * 
	 * @param mes
	 * @return
	 */
	public String retornaMes(String mes){
		String retorno ="";
		try {
			// Instância o SimpleDateFormat com o formato MM (esse formato indica o
			// formato de numeros).
			SimpleDateFormat sdf = new SimpleDateFormat("MM");
			// Faz o parse ("transforma") a String que contêm o mês em um Date.
			Date mesDate = sdf.parse(mes);
			// Altera o pattern do SimpleDateFormat.
			sdf.applyPattern("MMMM");
			// Retorna o nome do Mês.
			retorno = sdf.format(mesDate);
		} catch (Exception e) {}
		return retorno;
	}
	
	/**
	 * 
	 * Retorna nome arquivo.
	 * 
	 * @param recurso
	 * @return
	 */
	public String gerarNomeArquivo(){
		SimpleDateFormat formatterAno = new SimpleDateFormat("dd_MM_yyyy___HHmmss");
		Calendar cal = Calendar.getInstance();
		return formatterAno.format(cal.getTime());
	}
	
}
