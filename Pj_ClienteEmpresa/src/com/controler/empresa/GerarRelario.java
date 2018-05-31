package com.controler.empresa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.controler.cliente.SalvarCliente;
import com.utils.Utils;
import com.vo.ClienteVO;
import com.vo.EmpresaVO;

public class GerarRelario {

	public void gerarRelatorio(List<EmpresaVO> listaEmpresa) throws FileNotFoundException, IOException, ClassNotFoundException{
		Utils utils = new Utils();
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("Lista de empresas");
		
		HSSFCell celula;
		HSSFRow linha = null;
		
		// Cabeçalho
		String[] heads = { "id", "Nome Empresa", "Telefone", "Fax", "Endereço",
				"Número", "Complemento", "Cidade", "Estado", "Data Cadastro",
				"Cliente" };
		
		// largura celula recurso
		for (int i = 0; i < heads.length; i++) {
			if(i == 0){
				sheet.setColumnWidth((short) i, (short)(2000));	
			}else{
				sheet.setColumnWidth((short) i, (short)(5000));	
			}
		}
		
		//número da linha da vez
		int numLinha = 0;
		
		//cria linha.
		linha = sheet.createRow(numLinha);
		
		// cria Cabecalho.
		for (int i = 0; i < heads.length; i++) {
			createCell(wb, linha, i, null, numLinha, sheet, heads[i]);	
		}
		
		int numCelula = 0;
		for (Iterator iterator = listaEmpresa.iterator(); iterator.hasNext();) {
			EmpresaVO vo = (EmpresaVO) iterator.next();			
			linha = sheet.createRow(++numLinha);
			createCell(wb, linha, numCelula, null, numLinha, sheet, String.valueOf(vo.getIdEmpresa()));
			createCell(wb, linha, ++numCelula, null, numLinha, sheet, vo.getNome());
			createCell(wb, linha, ++numCelula, null, numLinha, sheet, vo.getTelefone());
			createCell(wb, linha, ++numCelula, null, numLinha, sheet, vo.getTelfax());
			createCell(wb, linha, ++numCelula, null, numLinha, sheet, vo.getEndereco());
			createCell(wb, linha, ++numCelula, null, numLinha, sheet, vo.getNumero());
			createCell(wb, linha, ++numCelula, null, numLinha, sheet, vo.getComplemento());
			createCell(wb, linha, ++numCelula, null, numLinha, sheet, vo.getCidade());
			createCell(wb, linha, ++numCelula, null, numLinha, sheet, vo.getEstado());
			createCell(wb, linha, ++numCelula, null, numLinha, sheet, vo.getDataCadastro());
			createCell(wb, linha, 0, null, numLinha, sheet, retornaNomeCliente(vo.getIdCliente()));
			numCelula = 0;			
		}
		
		String file = "C://TesteApontamento//2_2011//" + utils.gerarNomeArquivo() + ".xls";
		FileOutputStream fileOut = new FileOutputStream(file);
		wb.write(fileOut);
		fileOut.close();		
		Runtime.getRuntime().exec("cmd /c start excel.exe "+ file);
		 
		
	}
	
	/**
	 * Gera celulas com dados comuns.
	 * 
	 * @param wb
	 * @param row
	 * @param celula
	 * @param estilo
	 * @param numlinha
	 * @param sheet
	 * @param valor
	 */
	private static void createCell(HSSFWorkbook wb, HSSFRow row, int celula, HSSFCellStyle estilo, int numlinha, HSSFSheet sheet, String valor) {
		HSSFCell valorCelula = row.createCell((short) celula);		
		valorCelula.setCellValue(valor);
		//valorCelula.setCellStyle(estilo);
	}
	
	/**
	 * Retorna nome do cliente
	 * @param cod
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private String retornaNomeCliente(int cod) throws FileNotFoundException, IOException, ClassNotFoundException{
		SalvarCliente sc = new SalvarCliente();
		ClienteVO cliVOAux = sc.buscarClienteCod(cod);
		String nomeCliente = cliVOAux.getNome();
		return nomeCliente;
	}
}
