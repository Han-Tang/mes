<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv=Content-Type content="text/html; charset=gb2312">
<meta name=Generator content="Microsoft Word 12 (filtered)">
<style>

/* Font Definitions */
@font-face {
	font-family: 宋体;
	panose-1: 2 1 6 0 3 1 1 1 1 1;
}

@font-face {
	font-family: "Cambria Math";
	panose-1: 2 4 5 3 5 4 6 3 2 4;
}

@font-face {
	font-family: Calibri;
	panose-1: 2 15 5 2 2 2 4 3 2 4;
}

@font-face {
	font-family: 微软雅黑;
	panose-1: 2 11 5 3 2 2 4 2 2 4;
}

@font-face {
	font-family: "\@微软雅黑";
	panose-1: 2 11 5 3 2 2 4 2 2 4;
}

@font-face {
	font-family: "\@宋体";
	panose-1: 2 1 6 0 3 1 1 1 1 1;
}
/* Style Definitions */
p.MsoNormal, li.MsoNormal, div.MsoNormal {
	margin: 0cm;
	margin-bottom: .0001pt;
	text-align: center;
	text-justify: inter-ideograph;
	font-size: 10.5pt;
	font-family: "Calibri", "sans-serif";
}

h1 {
	mso-style-link: "标题 1 Char";
	margin-top: 17.0pt;
	margin-right: 0cm;
	margin-bottom: 16.5pt;
	margin-left: 0cm;
	text-align: center;
	text-justify: inter-ideograph;
	line-height: 240%;
	page-break-after: avoid;
	font-size: 22.0pt;
	font-family: "Calibri", "sans-serif";
}

h2 {
	mso-style-link: "标题 2 Char";
	margin-top: 13.0pt;
	margin-right: 0cm;
	margin-bottom: 13.0pt;
	margin-left: 0cm;
	text-align: center;
	text-justify: inter-ideograph;
	line-height: 173%;
	page-break-after: avoid;
	font-size: 16.0pt;
	font-family: "Cambria", "serif";
}

h3 {
	mso-style-link: "标题 3 Char";
	margin-top: 13.0pt;
	margin-right: 0cm;
	margin-bottom: 13.0pt;
	margin-left: 0cm;
	text-align: center;
	text-justify: inter-ideograph;
	line-height: 173%;
	page-break-after: avoid;
	font-size: 16.0pt;
	font-family: "Calibri", "sans-serif";
}

p.MsoToc1, li.MsoToc1, div.MsoToc1 {
	margin-top: 8.0pt;
	margin-right: 0cm;
	margin-bottom: 6.0pt;
	margin-left: 0cm;
	text-align: center;
	font-size: 10.0pt;
	font-family: "Calibri", "sans-serif";
	text-transform: uppercase;
	font-weight: bold;
}

p.MsoToc2, li.MsoToc2, div.MsoToc2 {
	margin-top: 0cm;
	margin-right: 0cm;
	margin-bottom: 0cm;
	margin-left: 10.5pt;
	margin-bottom: .0001pt;
	font-size: 10.0pt;
	font-family: "Calibri", "sans-serif";
	font-variant: small-caps;
}

p.MsoToc3, li.MsoToc3, div.MsoToc3 {
	margin-top: 0cm;
	margin-right: 0cm;
	margin-bottom: 0cm;
	margin-left: 21.0pt;
	margin-bottom: .0001pt;
	font-size: 10.0pt;
	font-family: "Calibri", "sans-serif";
	font-style: italic;
}

p.MsoToc4, li.MsoToc4, div.MsoToc4 {
	margin-top: 0cm;
	margin-right: 0cm;
	margin-bottom: 0cm;
	margin-left: 31.5pt;
	margin-bottom: .0001pt;
	font-size: 9.0pt;
	font-family: "Calibri", "sans-serif";
}

p.MsoToc5, li.MsoToc5, div.MsoToc5 {
	margin-top: 0cm;
	margin-right: 0cm;
	margin-bottom: 0cm;
	margin-left: 42.0pt;
	margin-bottom: .0001pt;
	font-size: 9.0pt;
	font-family: "Calibri", "sans-serif";
}

p.MsoToc6, li.MsoToc6, div.MsoToc6 {
	margin-top: 0cm;
	margin-right: 0cm;
	margin-bottom: 0cm;
	margin-left: 52.5pt;
	margin-bottom: .0001pt;
	font-size: 9.0pt;
	font-family: "Calibri", "sans-serif";
}

p.MsoToc7, li.MsoToc7, div.MsoToc7 {
	margin-top: 0cm;
	margin-right: 0cm;
	margin-bottom: 0cm;
	margin-left: 63.0pt;
	margin-bottom: .0001pt;
	font-size: 9.0pt;
	font-family: "Calibri", "sans-serif";
}

p.MsoToc8, li.MsoToc8, div.MsoToc8 {
	margin-top: 0cm;
	margin-right: 0cm;
	margin-bottom: 0cm;
	margin-left: 73.5pt;
	margin-bottom: .0001pt;
	font-size: 9.0pt;
	font-family: "Calibri", "sans-serif";
}

p.MsoToc9, li.MsoToc9, div.MsoToc9 {
	margin-top: 0cm;
	margin-right: 0cm;
	margin-bottom: 0cm;
	margin-left: 84.0pt;
	margin-bottom: .0001pt;
	font-size: 9.0pt;
	font-family: "Calibri", "sans-serif";
}

p.MsoHeader, li.MsoHeader, div.MsoHeader {
	mso-style-link: "页眉 Char";
	margin: 0cm;
	margin-bottom: .0001pt;
	text-align: center;
	layout-grid-mode: char;
	border: none;
	padding: 0cm;
	font-size: 9.0pt;
	font-family: "Calibri", "sans-serif";
}

p.MsoFooter, li.MsoFooter, div.MsoFooter {
	mso-style-link: "页脚 Char";
	margin: 0cm;
	margin-bottom: .0001pt;
	layout-grid-mode: char;
	font-size: 9.0pt;
	font-family: "Calibri", "sans-serif";
}

p.MsoDate, li.MsoDate, div.MsoDate {
	mso-style-link: "日期 Char";
	margin-top: 0cm;
	margin-right: 0cm;
	margin-bottom: 0cm;
	margin-left: 5.0pt;
	margin-bottom: .0001pt;
	text-align: center;
	text-justify: inter-ideograph;
	font-size: 10.5pt;
	font-family: "Calibri", "sans-serif";
}

a:link, span.MsoHyperlink {
	color: blue;
	text-decoration: underline;
}

a:visited, span.MsoHyperlinkFollowed {
	color: purple;
	text-decoration: underline;
}

p.MsoDocumentMap, li.MsoDocumentMap, div.MsoDocumentMap {
	mso-style-link: "文档结构图 Char";
	margin: 0cm;
	margin-bottom: .0001pt;
	text-align: center;
	text-justify: inter-ideograph;
	font-size: 9.0pt;
	font-family: 宋体;
}

p.MsoAcetate, li.MsoAcetate, div.MsoAcetate {
	mso-style-link: "批注框文本 Char";
	margin: 0cm;
	margin-bottom: .0001pt;
	text-align: center;
	text-justify: inter-ideograph;
	font-size: 9.0pt;
	font-family: "Calibri", "sans-serif";
}

p.MsoTocHeading, li.MsoTocHeading, div.MsoTocHeading {
	margin-top: 24.0pt;
	margin-right: 0cm;
	margin-bottom: 0cm;
	margin-left: 0cm;
	margin-bottom: .0001pt;
	line-height: 115%;
	page-break-after: avoid;
	font-size: 14.0pt;
	font-family: "Cambria", "serif";
	color: #365F91;
	font-weight: bold;
}

span.Char {
	mso-style-name: "页眉 Char";
	mso-style-link: 页眉;
}

span.Char0 {
	mso-style-name: "页脚 Char";
	mso-style-link: 页脚;
}

span.Char1 {
	mso-style-name: "日期 Char";
	mso-style-link: 日期;
}

span.Char2 {
	mso-style-name: "批注框文本 Char";
	mso-style-link: 批注框文本;
}

span.1Char {
	mso-style-name: "标题 1 Char";
	mso-style-link: "标题 1";
	font-weight: bold;
}

span.Char3 {
	mso-style-name: "文档结构图 Char";
	mso-style-link: 文档结构图;
	font-family: 宋体;
}

span.2Char {
	mso-style-name: "标题 2 Char";
	mso-style-link: "标题 2";
	font-family: "Cambria", "serif";
	font-weight: bold;
}

span.3Char {
	mso-style-name: "标题 3 Char";
	mso-style-link: "标题 3";
	font-weight: bold;
}
/* Page Definitions */
@page WordSection1 {
	size: 595.3pt 841.9pt;
	margin: 72.0pt 90.0pt 72.0pt 90.0pt;
	layout-grid: 15.6pt;
}

div.WordSection1 {
	page: WordSection1;
}
</style>

</head>

<body lang=ZH-CN link=blue vlink=purple
	style='text-justify-trim: punctuation'>

	<div class=WordSection1 style='layout-grid: 15.6pt'>


		<h1
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; line-height: 150%; text-align: center'>
			<a name="_Toc477824978"><span
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体'>第一章
					建立工厂</span></a>
		</h1>

		<h2
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%; text-align: center'>
			<a name="_Toc477824979"><span lang=EN-US
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>1.1</span></a><span
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>登录<span
				lang=EN-US>IE-MES</span></span>
		</h2>

		<p class=MsoNormal
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; line-height: 150%'>
			<span
				lang=EN-US style='line-height: 150%; font-family: 宋体'>http://120.77.147.255/IE-MES</span></a>
		</p>

		<p class=MsoNormal
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; line-height: 150%'>
			<span style='line-height: 150%; font-family: 宋体'>输入开通的账号、密码并选择所属的站点<span
				lang=EN-US>,</span>站点是创建的实训工厂标志，产品试用阶段由公司工程师创建好试用站点，一般由学校或学院命名，然后分发给试用人或试用单位。
			</span>
		</p>

		<p class=MsoNormal
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; line-height: 150%'>
			<span lang=EN-US style='line-height: 150%; font-family: 宋体'><img
				width=554 height=308 id="图片 1" src="${ctx}/help.files/image001.jpg"></span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span style='line-height: 150%; font-family: 宋体'>登录首页</span>
		</p>

		<h2
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<a name="_Toc477824980"><span lang=EN-US
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>1.1</span></a><span
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>建立站点</span>
		</h2>

		<p class=MsoNormal
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; line-height: 150%'>
			<span lang=EN-US
				style='line-height: 150%; font-family: 宋体; color: red'>*</span><span
				style='line-height: 150%; font-family: 宋体; color: red'>建立站点属于管理员操作权限，产品试用阶段，由工程师创建，并分发账号及密码信息。</span>
		</p>

		<p class=MsoNormal
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; line-height: 150%'>
			<span style='line-height: 150%; font-family: 宋体'>步骤：进入首页，点击左侧菜单的“车间基础建模”<span
				lang=EN-US>-&gt;</span>“站点维护”，点击“新增”按钮，填写站点信息，并保存。用户需退出系统，再次选择站点进行登录。
			</span>
		</p>

		<p class=MsoNormal
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; line-height: 150%'>
			<span lang=EN-US style='line-height: 150%; font-family: 宋体'><img
				width=554 height=220 id="图片 4" src="${ctx}/help.files/image002.jpg"></span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span style='line-height: 150%; font-family: 宋体'>站点维护页面</span>
		</p>

		<p class=MsoNormal align=centerstyle='margin-top:7.8pt;margin-right:0cm;
margin-bottom:7.8pt;margin-left:0cm;text-align:left;line-height:150%'>
			<span style='line-height: 150%; font-family: 宋体'>站点编号采用英文、数字组合，建议不含中文或特殊字符。</span>
		</p>

		<p class=MsoNormal align=centerstyle='margin-top:7.8pt;margin-right:0cm;
margin-bottom:7.8pt;margin-left:0cm;text-align:left;line-height:150%'>
			<span style='line-height: 150%; font-family: 宋体'>站点名称、站点描述可以包含中文。</span>
		</p>

		<h2
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<a name="_Toc477824981"><span lang=EN-US
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>1.2</span></a><span
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>建立车间</span>
		</h2>

		<p class=MsoNormal
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; line-height: 150%'>
			<span style='line-height: 150%; font-family: 宋体'>进入首页，点击左侧菜单的“车间基础建模”<span
				lang=EN-US>-&gt;</span>“工作中心”，点击“新增”按钮，填写车间信息，并保存。
			</span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span lang=EN-US><img width=554 height=289 id="图片 7"
				src="${ctx}/help.files/image003.jpg"></span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span style='font-family: 宋体'>新建车间</span>
		</p>

		<p class=MsoNormal
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; line-height: 150%'>
			<span style='line-height: 150%; font-family: 宋体'>工作中心层级需选择车间，工作中心类型任意选择，父工作中心无需填写。</span>
		</p>

		<h2
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<a name="_Toc477824982"><span lang=EN-US
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>1.3</span></a><span
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>建立生产线</span>
		</h2>

		<p class=MsoNormal
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; line-height: 150%'>
			<span style='line-height: 150%; font-family: 宋体'>进入首页，点击左侧菜单的“车间基础建模”<span
				lang=EN-US>-&gt;</span>“工作中心”，点击“新增”按钮，填写站点信息，并保存。
			</span>
		</p>

		<p class=MsoNormal
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<span style='font-family: 宋体'>注：选择创建的车间作为父工作中心。</span>
		</p>

		<p class=MsoNormal
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<span lang=EN-US><img width=554 height=273 id="图片 10"
				src="${ctx}/help.files/image004.jpg"></span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span style='font-family: 宋体'>新建生产线</span>
		</p>

		<p class=MsoNormal
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<span style='font-family: 宋体'>工作中心层级选择生产线，工作中心类型任意选择，父工作中心选择</span><span
				lang=EN-US>1.2</span><span style='font-family: 宋体'>中创建的车间。</span>
		</p>

		<h2
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<a name="_Toc477824983"><span lang=EN-US
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>1.4</span></a><span
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>建立工序</span>
		</h2>

		<p class=MsoNormal
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; line-height: 150%'>
			<span style='line-height: 150%; font-family: 宋体'>进入生产操作员面板<span
				lang=EN-US>-&gt;</span>按钮维护，维护对应的<span lang=EN-US>POD</span>。
			</span>
		</p>

		<p class=MsoNormal
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; line-height: 150%'>
			<span lang=EN-US style='line-height: 150%; font-family: 宋体'><img
				width=554 height=268 id="图片 70"
				src="${ctx}/help.files/image005.jpg"></span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span lang=EN-US style='line-height: 150%; font-family: 宋体'>POD</span><span
				style='line-height: 150%; font-family: 宋体'>开始按钮维护</span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span lang=EN-US style='line-height: 150%; font-family: 宋体'><img
				width=554 height=268 id="图片 73"
				src="${ctx}/help.files/image006.jpg"></span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span lang=EN-US style='line-height: 150%; font-family: 宋体'>POD</span><span
				style='line-height: 150%; font-family: 宋体'>完成按钮维护</span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span lang=EN-US style='line-height: 150%; font-family: 宋体'><img
				width=554 height=273 id="图片 76"
				src="${ctx}/help.files/image007.jpg"></span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span lang=EN-US style='line-height: 150%; font-family: 宋体'>POD</span><span
				style='line-height: 150%; font-family: 宋体'>装配按钮维护</span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span lang=EN-US style='line-height: 150%; font-family: 宋体'><img
				width=554 height=275 src="${ctx}/help.files/image008.jpg"></span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span lang=EN-US style='line-height: 150%; font-family: 宋体'>POD</span><span
				style='line-height: 150%; font-family: 宋体'>记录不良按钮维护</span>
		</p>

		<p class=MsoNormal
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; line-height: 150%'>
			<span style='line-height: 150%; font-family: 宋体'>进入首页，点击左侧菜单的“车间基础建模”<span
				lang=EN-US>-&gt;</span>“操作维护”，点击“新增”按钮，填写站点信息，并保存。
			</span>
		</p>

		<p class=MsoNormal
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; line-height: 150%'>
			<span lang=EN-US style='line-height: 150%; font-family: 宋体'><img
				width=554 height=295 src="${ctx}/help.files/image009.jpg"></span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span style='font-family: 宋体'>新建装配工序</span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span lang=EN-US><img width=554 height=296
				src="${ctx}/help.files/image010.jpg"></span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span style='font-family: 宋体'>新建检测工序</span>
		</p>

		<p class=MsoNormal
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<span style='font-family: 宋体'>所属工作中心选择为新建的生产线。</span>
		</p>

		<h2
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<a name="_Toc477824984"><span lang=EN-US
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>1.5</span></a><span
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>建立资源（工序上的操作员或设备）</span>
		</h2>

		<p class=MsoNormal
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; line-height: 150%'>
			<span style='line-height: 150%; font-family: 宋体'>进入首页，点击左侧菜单的“车间基础建模”<span
				lang=EN-US>-&gt;</span>“资源维护”，点击“新增”按钮，填写站点信息，并保存。
			</span>
		</p>

		<p class=MsoNormal
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<span lang=EN-US><img width=554 height=220 id="图片 19"
				src="${ctx}/help.files/image011.jpg"></span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span style='font-family: 宋体'>新建装配工序</span><span lang=EN-US>P01</span><span
				style='font-family: 宋体'>的资源—装配设备一号</span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span lang=EN-US><img width=554 height=203 id="图片 22"
				src="${ctx}/help.files/image012.jpg"></span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span style='font-family: 宋体'>新建检测工序</span><span lang=EN-US>P02</span><span
				style='font-family: 宋体'>的资源—检测设备一号</span>
		</p>

		<p class=MsoNormal
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<span style='font-family: 宋体'>至此，系统中的工厂已经建立。</span>
		</p>

		<h1
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; line-height: 150%'>
			<a name="_Toc477824985"><span
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体'>第二章
					维护产品物料</span></a>
		</h1>

		<h2
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<a name="_Toc477824986"><span lang=EN-US
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>2.1</span></a><span
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>维护装配组件物料</span>
		</h2>

		<p class=MsoNormal
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; line-height: 150%'>
			<span style='line-height: 150%; font-family: 宋体'>进入首页，点击左侧菜单的“生产物料控制”<span
				lang=EN-US>-&gt;</span>“物料维护”，点击“新增”按钮，填写站点信息，并保存。
			</span>
		</p>

		<p class=MsoNormal
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; line-height: 150%'>
			<span lang=EN-US style='line-height: 150%; font-family: 宋体'><img
				width=554 height=238 id="图片 25"
				src="${ctx}/help.files/image013.jpg"></span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span style='line-height: 150%; font-family: 宋体'>新建后壳物料</span>
		</p>

		<p class=MsoNormal
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<span lang=EN-US><img width=554 height=258 id="图片 28"
				src="${ctx}/help.files/image014.jpg"></span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span style='font-family: 宋体'>新建主板物料</span>
		</p>

		<p class=MsoNormal
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<span lang=EN-US><img width=554 height=242 id="图片 31"
				src="${ctx}/help.files/image015.jpg"></span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span style='font-family: 宋体'>新建</span><span lang=EN-US>WIFI</span><span
				style='font-family: 宋体'>模组物料</span>
		</p>

		<h2
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<a name="_Toc477824987"><span lang=EN-US
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>2.2</span></a><span
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>维护物料清单</span>
		</h2>

		<p class=MsoNormal
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; line-height: 150%'>
			<span style='line-height: 150%; font-family: 宋体'>进入首页，点击左侧菜单的“生产物料控制”<span
				lang=EN-US>-&gt;</span>“物料清单维护”，点击“新增”按钮，填写站点信息，并保存。
			</span>
		</p>

		<p class=MsoNormal
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; line-height: 150%'>
			<span lang=EN-US style='line-height: 150%; font-family: 宋体'><img
				width=554 height=287 id="图片 34"
				src="${ctx}/help.files/image016.jpg"></span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span style='font-family: 宋体'>新建手机物料清单</span>
		</p>

		<p class=MsoNormal align=centerstyle='margin-top:2.5pt;margin-right:0cm;
margin-bottom:2.5pt;margin-left:0cm;text-align:left;line-height:150%'>
			<span style='font-family: 宋体'>物料清单子项目依次选择</span><span lang=EN-US>2.1</span><span
				style='font-family: 宋体'>维护的组件物料，并设置装配数量，对应的结存的上下限。</span>
		</p>

		<h2
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<a name="_Toc477824988"><span lang=EN-US
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>2.3</span></a><span
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>维护成品物料</span>
		</h2>

		<p class=MsoNormal align=center
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span lang=EN-US><img width=554 height=238 id="图片 37"
				src="${ctx}/help.files/image017.jpg"></span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span style='font-family: 宋体'>新建手机成品物料</span>
		</p>

		<p class=MsoNormal
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<span style='font-family: 宋体'>成品物料需要选择装配的物料清单，即</span><span
				lang=EN-US>2.2</span><span style='font-family: 宋体'>维护的手机成品物料，并勾选物料类型为自生产。</span>
		</p>

		<h2
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<a name="_Toc477824989"><span lang=EN-US
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>2.4</span></a><span
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>维护工艺路线</span>
		</h2>

		<p class=MsoNormal
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; line-height: 150%'>
			<span style='line-height: 150%; font-family: 宋体'>进入首页，点击左侧菜单的“车间基础建模”<span
				lang=EN-US>-&gt;</span>“工艺路线”，点击“新增”按钮，填写站点信息，并保存。
			</span>
		</p>

		<p class=MsoNormal
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; line-height: 150%'>
			<span lang=EN-US style='line-height: 150%; font-family: 宋体'><img
				width=554 height=285 id="图片 40"
				src="${ctx}/help.files/image018.jpg"></span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span style='line-height: 150%; font-family: 宋体'>新建工艺路线</span>
		</p>

		<p class=MsoNormal
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<span style='font-family: 宋体'>按照工序的顺序，选择左边的箭头，进行连接。</span>
		</p>

		<h1
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; line-height: 150%'>
			<a name="_Toc477824990"><span
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体'>第三章
					品质建模</span></a>
		</h1>

		<h2
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<a name="_Toc477824991"><span lang=EN-US
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>3.1</span></a><span
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>不合格代码组维护</span>
		</h2>

		<p class=MsoNormal
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; line-height: 150%'>
			<span style='line-height: 150%; font-family: 宋体'>进入首页，点击左侧菜单的“产品质量管理”<span
				lang=EN-US>-&gt;</span>“不合格代码组维护”，点击“新增”按钮，填写
			</span><span style='font-family: 宋体'>不合格代码组</span><span
				style='line-height: 150%; font-family: 宋体'>信息，并保存。</span>
		</p>

		<p class=MsoNormal
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<span lang=EN-US><img width=554 height=243 id="图片 43"
				src="${ctx}/help.files/image019.jpg"></span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span style='font-family: 宋体'>新建不合格代码组</span>
		</p>

		<h2
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<a name="_Toc477824992"><span lang=EN-US
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>3.2</span></a><span
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>不合格代码维护</span>
		</h2>

		<p class=MsoNormal
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; line-height: 150%'>
			<span style='line-height: 150%; font-family: 宋体'>进入首页，点击左侧菜单的“产品质量管理”<span
				lang=EN-US>-&gt;</span>“不合格代码维护”，点击“新增”按钮，填写不良代码信息，并保存。
			</span>
		</p>

		<p class=MsoNormal
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<span lang=EN-US><img width=554 height=235 id="图片 46"
				src="${ctx}/help.files/image020.jpg"></span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span style='font-family: 宋体'>新建不合格代码</span>
		</p>

		<h1
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; line-height: 150%'>
			<a name="_Toc477824993"><span
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体'>第四章
					生产计划下达</span></a>
		</h1>

		<h2
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<a name="_Toc477824994"><span lang=EN-US
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>4.1</span></a><span
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>计划工单维护</span>
		</h2>

		<p class=MsoNormal
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; line-height: 150%'>
			<span style='line-height: 150%; font-family: 宋体'>进入首页，点击左侧菜单的“生产计划管理”<span
				lang=EN-US>-&gt;</span>“计划工单维护”，点击“新增”按钮，填写工单信息，并保存。
			</span>
		</p>

		<p class=MsoNormal
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<span lang=EN-US><img width=554 height=265 id="图片 49"
				src="${ctx}/help.files/image021.jpg"></span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span style='font-family: 宋体'>新建计划工单</span>
		</p>

		<p class=MsoNormal align=centerstyle='margin-top:2.5pt;margin-right:0cm;
margin-bottom:2.5pt;margin-left:0cm;text-align:left;line-height:150%'>
			<span style='font-family: 宋体'>物料选择</span><span lang=EN-US>2.3</span><span
				style='font-family: 宋体'>维护的成品物料，工艺路线选择</span><span lang=EN-US>2.4</span><span
				style='font-family: 宋体'>维护的工艺路线。</span>
		</p>

		<h2
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<a name="_Toc477824995"><span lang=EN-US
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>4.2</span></a><span
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>计划工单下达</span>
		</h2>

		<p class=MsoNormal
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; line-height: 150%'>
			<span style='line-height: 150%; font-family: 宋体'>进入首页，点击左侧菜单的“生产计划管理”<span
				lang=EN-US>-&gt;</span>“计划工单下达”，保存工单下达信息。
			</span>
		</p>

		<p class=MsoNormal
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; line-height: 150%'>
			<span lang=EN-US style='line-height: 150%; font-family: 宋体'><img
				width=554 height=285 id="图片 52"
				src="${ctx}/help.files/image022.jpg"></span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span style='line-height: 150%; font-family: 宋体'>选中未完成工单</span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span lang=EN-US style='line-height: 150%; font-family: 宋体'><img
				width=554 height=307 id="图片 55"
				src="${ctx}/help.files/image023.jpg"></span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span lang=EN-US style='line-height: 150%; font-family: 宋体'><img
				width=553 height=86 id="图片 58" src="${ctx}/help.files/image024.jpg"></span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span style='font-family: 宋体'>计划工单下达</span>
		</p>

		<h1
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; line-height: 150%'>
			<a name="_Toc477824996"><span
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体'>第五章
					库存接收</span></a>
		</h1>

		<h2
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<a name="_Toc477824997"><span lang=EN-US
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>5.1</span></a><span
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>车间库存接收</span>
		</h2>

		<p class=MsoNormal
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; line-height: 150%'>
			<span style='line-height: 150%; font-family: 宋体'>进入首页，点击左侧菜单的“车间库存管理”<span
				lang=EN-US>-&gt;</span>“车间库存接收”。
			</span>
		</p>

		<p class=MsoNormal
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<span lang=EN-US><img width=554 height=259 id="图片 61"
				src="${ctx}/help.files/image025.jpg"></span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span style='font-family: 宋体'>后壳车间库存接收</span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span lang=EN-US><img width=554 height=265 id="图片 64"
				src="${ctx}/help.files/image026.jpg"></span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span style='font-family: 宋体'>主板车间库存接收</span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span lang=EN-US><img width=553 height=260 id="图片 67"
				src="${ctx}/help.files/image027.jpg"></span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span lang=EN-US>WIFI</span><span style='font-family: 宋体'>车间库存接收</span>
		</p>

		<h1
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; line-height: 150%'>
			<a name="_Toc477824998"><span
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体'>第六章
					执行生产</span></a>
		</h1>

		<p class=MsoNormal
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; line-height: 150%'>
			<span style='line-height: 150%; font-family: 宋体'>进入首页，点击左侧菜单的“生产过程控制”<span
				lang=EN-US>-&gt;</span>“生产操作员面板<span lang=EN-US>-</span>操作”。选择生产的工单，对应的操作和资源，在<span
				lang=EN-US>SFC</span>输入框中输入需要生产的<span lang=EN-US>SFC</span>。
			</span>
		</p>

		<h2
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<a name="_Toc477824999"><span lang=EN-US
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>6.1
					SFC</span></a><span
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>开始</span>
		</h2>

		<p class=MsoNormal
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<span style='font-family: 宋体'>点击开始按钮，加载该</span><span lang=EN-US>SFC</span><span
				style='font-family: 宋体'>状态信息。</span>
		</p>

		<p class=MsoNormal
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<span lang=EN-US><img width=554 height=202
				src="${ctx}/help.files/image028.jpg"></span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span lang=EN-US>SFC</span><span style='font-family: 宋体'>开始</span>
		</p>

		<h2
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<a name="_Toc477825000"><span lang=EN-US
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>6.2
					SFC</span></a><span
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>装配</span>
		</h2>

		<p class=MsoNormal
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<span style='font-family: 宋体'>点击装配，根据物料清单加载需要装配的组件物料。</span>
		</p>

		<p class=MsoNormal
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<span lang=EN-US><img width=554 height=308
				src="${ctx}/help.files/image029.jpg"></span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span style='font-family: 宋体'>装配</span>
		</p>

		<p class=MsoNormal
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<span style='font-family: 宋体'>选中当前装配的组件，然后点击装配按钮：</span>
		</p>

		<p class=MsoNormal
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<span lang=EN-US><img width=554 height=300 id="图片 97"
				src="${ctx}/help.files/image030.jpg"></span>
		</p>

		<p class=MsoNormal
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<span style='font-family: 宋体'>填入车间库存接收的组件物料编号</span><span lang=EN-US>SFC</span><span
				style='font-family: 宋体'>，单击装配，显示装配成功。</span>
		</p>

		<p class=MsoNormal
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<span lang=EN-US><img width=553 height=300 id="图片 100"
				src="${ctx}/help.files/image031.jpg"></span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span style='font-family: 宋体'>装配组件成功</span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span lang=EN-US><img width=553 height=298 id="图片 103"
				src="${ctx}/help.files/image032.jpg"></span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span style='font-family: 宋体'>所有组件装配成功</span>
		</p>

		<h2
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<a name="_Toc477825001"><span lang=EN-US
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>6.3
					SFC</span></a><span
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>记录不良</span>
		</h2>

		<p class=MsoNormal
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<span style='font-family: 宋体'>针对不良品，点击记录不良按钮，选择记录的不良代码组和不良代码，点击记录不合格。</span>
		</p>

		<p class=MsoNormal
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<span lang=EN-US><img width=554 height=281 id="图片 109"
				src="${ctx}/help.files/image033.jpg"></span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span style='font-family: 宋体'>显示不良记录页面</span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span lang=EN-US>&nbsp;</span>
		</p>

		<h2
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<a name="_Toc477825002"><span lang=EN-US
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>6.4
					SFC</span></a><span
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>完成</span>
		</h2>

		<p class=MsoNormal
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<span lang=EN-US><img width=554 height=276 id="图片 106"
				src="${ctx}/help.files/image034.jpg"></span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span style='font-family: 宋体'>单击完成按钮</span>
		</p>

		<p class=MsoNormal align=centerstyle='margin-top:2.5pt;margin-right:0cm;
margin-bottom:2.5pt;margin-left:0cm;text-align:left;line-height:150%'>
			<span style='font-family: 宋体'>产品生产完成操作。</span>
		</p>

		<h1
			style='margin-top: 7.8pt; margin-right: 0cm; margin-bottom: 7.8pt; margin-left: 0cm; line-height: 150%'>
			<a name="_Toc477825003"><span
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体'>第七章
					查阅报表</span></a>
		</h1>

		<h2
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<a name="_Toc477825004"><span lang=EN-US
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>7.1</span></a><span
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>车间库存消耗看板</span>
		</h2>

		<p class=MsoNormal align=centerstyle='margin-top:2.5pt;margin-right:0cm;
margin-bottom:2.5pt;margin-left:0cm;text-align:left;line-height:150%'>
			<span lang=EN-US><img width=554 height=266 id="图片 79"
				src="${ctx}/help.files/image035.jpg"></span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span style='font-family: 宋体'>车间库存消耗看板</span>
		</p>

		<h2
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<a name="_Toc477825005"><span lang=EN-US
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>7.2</span></a><span
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>产能报表</span>
		</h2>

		<p class=MsoNormal align=centerstyle='margin-top:2.5pt;margin-right:0cm;
margin-bottom:2.5pt;margin-left:0cm;text-align:left;line-height:150%'>
			<span lang=EN-US><img width=554 height=310 id="图片 82"
				src="${ctx}/help.files/image036.jpg"></span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span style='font-family: 宋体'>产能报表</span>
		</p>

		<h2
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<a name="_Toc477825006"><span lang=EN-US
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>7.3</span></a><span
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>正向追溯报表</span>
		</h2>

		<p class=MsoNormal align=centerstyle='margin-top:2.5pt;margin-right:0cm;
margin-bottom:2.5pt;margin-left:0cm;text-align:left;line-height:150%'>
			<span lang=EN-US><img width=554 height=259 id="图片 85"
				src="${ctx}/help.files/image037.jpg"></span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span style='font-family: 宋体'>正向追溯报表</span>
		</p>

		<h2
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<a name="_Toc477825007"><span lang=EN-US
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>7.4</span></a><span
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>反向追溯报表</span>
		</h2>

		<p class=MsoNormal align=centerstyle='margin-top:2.5pt;margin-right:0cm;
margin-bottom:2.5pt;margin-left:0cm;text-align:left;line-height:150%'>
			<span lang=EN-US><img width=554 height=312 id="图片 88"
				src="${ctx}/help.files/image038.jpg"></span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span style='font-family: 宋体'>反向追溯报表</span>
		</p>

		<h2
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<a name="_Toc477825008"><span lang=EN-US
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>7.5</span></a><span
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>不合格报表</span>
		</h2>

		<p class=MsoNormal align=centerstyle='margin-top:2.5pt;margin-right:0cm;
margin-bottom:2.5pt;margin-left:0cm;text-align:left;line-height:150%'>
			<span lang=EN-US><img width=554 height=310 id="图片 91"
				src="${ctx}/help.files/image039.jpg"></span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span style='font-family: 宋体'>不合格报表</span>
		</p>

		<h2
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; line-height: 150%'>
			<a name="_Toc477825009"><span lang=EN-US
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>7.6</span></a><span
				style='font-size: 10.5pt; line-height: 150%; font-family: 宋体; font-weight: normal'>车间库存报表</span>
		</h2>

		<p class=MsoNormal align=centerstyle='margin-top:2.5pt;margin-right:0cm;
margin-bottom:2.5pt;margin-left:0cm;text-align:left;line-height:150%'>
			<span lang=EN-US><img width=554 height=315 id="图片 94"
				src="${ctx}/help.files/image040.jpg"></span>
		</p>

		<p class=MsoNormal align=center
			style='margin-top: 2.5pt; margin-right: 0cm; margin-bottom: 2.5pt; margin-left: 0cm; text-align: center; line-height: 150%'>
			<span style='font-family: 宋体'>车间库存报表</span>
		</p>

	</div>

</body>

</html>
