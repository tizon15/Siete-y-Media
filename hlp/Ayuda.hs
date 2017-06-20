<?xml version='1.0' encoding = 'UTF-8' ?>
<helpset> 
	<title> Ayuda Siete Y Media  </title>
	<maps>
		<homeID> Bienvenida</homeID>
		<mapref location="Terminos.jhm"/> 
	</maps>
	<view>
		<name> Tabla de contenidos </name>
		<label> TOC </label>
		<type> javax.help.TOCView </type>
		<data> TOC.xml</data>
	</view>
	<view>
		<name> Palabras clave</name>
		<label> Indice </label>
		<type> javax.help.IndexView </type>
		<data> Indice.xml</data>
	</view>
	<view>
		<name> Buscar</name>
		<label> Busqueda </label>
		<type> javax.help.SearchView </type>
		<data engine="com.sun.java.help.search.DeafaultSearchEngine"> JavaHelpSearch </data>
	</view>
</helpset>