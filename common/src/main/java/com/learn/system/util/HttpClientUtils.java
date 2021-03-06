package com.learn.system.util;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.ObjectUtils;

/**
 * @Author: zben
 * @Description:
 * @Date: 下午1:34 2018/4/16
 */
public class HttpClientUtils {

	private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class); // 日志记录

	private static RequestConfig requestConfig = null;

	private static String OK = "200";

	static {
		// 设置请求和传输超时时间
		requestConfig = RequestConfig.custom().setSocketTimeout(50000).setConnectTimeout(50000).build();
	}

	/**
	 * post请求传输json参数
	 *
	 * @param url
	 *            url地址
	 * @param
	 *
	 * @return
	 */
	public static JSONObject post(String url, Map<String, String> params) {
		// post请求返回结果
		CloseableHttpClient httpClient = HttpClients.createDefault();
		JSONObject jsonResult = null;
		HttpPost httpPost = new HttpPost(url);
		// 设置请求和传输超时时间
		httpPost.setConfig(requestConfig);
		try {
			if (!ObjectUtils.isEmpty(params)) {
				// 解决中文乱码问题
				StringEntity entity = new StringEntity(params.toString(), "utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/json");
				httpPost.setEntity(entity);
			}
			CloseableHttpResponse result = httpClient.execute(httpPost);
			// 请求发送成功，并得到响应
			if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String str = "";
				try {
					// 读取服务器返回过来的json字符串数据
					str = EntityUtils.toString(result.getEntity(), "utf-8");
					// 把json字符串转换成json对象
					jsonResult = JSONObject.parseObject(str);
				} catch (Exception e) {
					logger.error("post请求提交失败: {}", url, e);
				}
			}
		} catch (IOException e) {
			logger.error("post请求提交失败: {}", url, e);
		} finally {
			httpPost.releaseConnection();
		}
		return jsonResult;
	}

	/**
	 * post请求传输json参数
	 *
	 * @param url
	 *            url地址
	 * @param params
	 *            参数
	 * @return
	 */
	public static JSONObject post(String url, Map<String, String> params, Map<String, String> headers) {
		// post请求返回结果
		CloseableHttpClient httpClient = HttpClients.createDefault();
		JSONObject jsonResult = null;
		HttpPost httpPost = new HttpPost(url);
		// 设置请求和传输超时时间
		httpPost.setConfig(requestConfig);
		try {
			if (!ObjectUtils.isEmpty(params)) {
				// 解决中文乱码问题
				StringEntity entity = new StringEntity(JSONUtil.toString(params), "utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/json");
				httpPost.setEntity(entity);
			}
			if (!ObjectUtils.isEmpty(headers)) {
				for (Map.Entry<String, String> entry : headers.entrySet()) {
					if (!StringUtils.isEmpty(entry.getKey())) {
						httpPost.setHeader(entry.getKey(), entry.getValue());
					}
				}
			}
			CloseableHttpResponse result = httpClient.execute(httpPost);
			// 请求发送成功，并得到响应
			if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String str = "";
				try {
					// 读取服务器返回过来的json字符串数据
					str = EntityUtils.toString(result.getEntity(), "utf-8");
					// 把json字符串转换成json对象
					jsonResult = JSONObject.parseObject(str);
				} catch (Exception e) {
					logger.error("post请求提交失败: {}", url, e);
				}
			}
		} catch (IOException e) {
			logger.error("post请求提交失败: {}", url, e);
		} finally {
			httpPost.releaseConnection();
		}
		return jsonResult;
	}

	/**
	 * post请求传输String参数 例如：name=Jack&sex=1&type=2
	 * Content-type: application/x-www-form-urlencoded
	 *
	 * @param url 请求地址
	 * @param queryStr  查询字符串
	 * @return
	 */
	public static JSONObject post(String url, String queryStr) {
		// post请求返回结果
		CloseableHttpClient httpClient = HttpClients.createDefault();
		JSONObject jsonResult = null;
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(requestConfig);
		try {
			if (null != queryStr) {
				// 解决中文乱码问题
				StringEntity entity = new StringEntity(queryStr, "utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/json");
				httpPost.setEntity(entity);
			}
			CloseableHttpResponse result = httpClient.execute(httpPost);
			// 请求发送成功，并得到响应
			if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String str = "";
				try {
					// 读取服务器返回过来的json字符串数据
					str = EntityUtils.toString(result.getEntity(), "utf-8");
					// 把json字符串转换成json对象
					jsonResult = JSONObject.parseObject(str);
				} catch (Exception e) {
					logger.error("post请求提交失败:" + url, e);
				}
			}
		} catch (Exception e) {
			logger.error("post请求提交失败:" + url, e);
		} finally {
			httpPost.releaseConnection();
		}
		return jsonResult;
	}

	/**
	 * post请求传输String参数 例如：name=Jack&sex=1&type=2
	 * Content-type: application/x-www-form-urlencoded
	 *
	 * @param url 请求地址
	 * @param queryStr  查询字符串
	 * @return
	 */
	public static JSONObject post(String url, String queryStr, Map<String, String> headers) {
		// post请求返回结果
		CloseableHttpClient httpClient = HttpClients.createDefault();
		JSONObject jsonResult = null;
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(requestConfig);
		try {
			// 主体参数
			if (!StringUtils.isEmpty(queryStr)) {
				// 解决中文乱码问题
				StringEntity entity = new StringEntity(queryStr, "utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/json");
				httpPost.setEntity(entity);
			}
			// 请求头
			if (!ObjectUtils.isEmpty(headers)) {
				for (Map.Entry<String, String> entry : headers.entrySet()) {
					if (!StringUtils.isEmpty(entry.getKey())) {
						httpPost.setHeader(entry.getKey(), entry.getValue());
					}
				}
			}
			CloseableHttpResponse result = httpClient.execute(httpPost);
			// 请求发送成功，并得到响应
			if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String str = "";
				try {
					// 读取服务器返回过来的json字符串数据
					str = EntityUtils.toString(result.getEntity(), "utf-8");
					// 把json字符串转换成json对象
					jsonResult = JSONObject.parseObject(str);
				} catch (Exception e) {
					logger.error("post请求提交失败:" + url, e);
				}
			}
		} catch (Exception e) {
			logger.error("post请求提交失败:" + url, e);
		} finally {
			httpPost.releaseConnection();
		}
		return jsonResult;
	}

	/**
	 * 发送get请求
	 *
	 * @param url
	 *            路径
	 * @return
	 */
	public static JSONObject get(String url) {
		// get请求返回结果
		JSONObject jsonResult = null;
		CloseableHttpClient client = HttpClients.createDefault();
		// 发送get请求
		HttpGet request = new HttpGet(url);
		request.setConfig(requestConfig);
		try {
			CloseableHttpResponse response = client.execute(request);

			// 请求发送成功，并得到响应
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// 读取服务器返回过来的json字符串数据
				HttpEntity entity = response.getEntity();
				String strResult = EntityUtils.toString(entity, "utf-8");
				// 把json字符串转换成json对象
				jsonResult = JSONObject.parseObject(strResult);
			} else {
				logger.error("get请求提交失败:" + url);
			}
		} catch (IOException e) {
			logger.error("get请求提交失败:" + url, e);
		} finally {
			request.releaseConnection();
		}
		return jsonResult;
	}




	/**
	 * post请求传输json参数
	 *
	 * @param url
	 *            url地址
	 * @param
	 *
	 * @return
	 */
	public static String postStr(String url, Map<String, String> params) {
		// post请求返回结果
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String result = null;
		HttpPost httpPost = new HttpPost(url);
		// 设置请求和传输超时时间
		httpPost.setConfig(requestConfig);
		try {
			if (!ObjectUtils.isEmpty(params)) {
				// 解决中文乱码问题
				StringEntity entity = new StringEntity(params.toString(), "utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/json");
				httpPost.setEntity(entity);
			}
			CloseableHttpResponse response = httpClient.execute(httpPost);
			// 请求发送成功，并得到响应
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String str = "";
				try {
					// 读取服务器返回过来的json字符串数据
					result = EntityUtils.toString(response.getEntity(), "utf-8");
				} catch (Exception e) {
					logger.error("post请求提交失败: {}", url, e);
				}
			}
		} catch (IOException e) {
			logger.error("post请求提交失败: {}", url, e);
		} finally {
			httpPost.releaseConnection();
		}
		return result;
	}

	/**
	 * post请求传输json参数
	 *
	 * @param url
	 *            url地址
	 * @param params
	 *            参数
	 * @return
	 */
	public static String postStr(String url, Map<String, String> params, Map<String, String> headers) {
		// post请求返回结果
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String result = null;
		HttpPost httpPost = new HttpPost(url);
		// 设置请求和传输超时时间
		httpPost.setConfig(requestConfig);
		try {
			if (!ObjectUtils.isEmpty(params)) {
				// 解决中文乱码问题
				StringEntity entity = new StringEntity(JSONUtil.toString(params), "utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/json");
				httpPost.setEntity(entity);
			}
			if (!ObjectUtils.isEmpty(headers)) {
				for (Map.Entry<String, String> entry : headers.entrySet()) {
					if (!StringUtils.isEmpty(entry.getKey())) {
						httpPost.setHeader(entry.getKey(), entry.getValue());
					}
				}
			}
			CloseableHttpResponse response = httpClient.execute(httpPost);
			// 请求发送成功，并得到响应
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String str = "";
				try {
					// 读取服务器返回过来的json字符串数据
					result = EntityUtils.toString(response.getEntity(), "utf-8");
				} catch (Exception e) {
					logger.error("post请求提交失败: {}", url, e);
				}
			}
		} catch (IOException e) {
			logger.error("post请求提交失败: {}", url, e);
		} finally {
			httpPost.releaseConnection();
		}
		return result;
	}

	/**
	 * post请求传输String参数 例如：name=Jack&sex=1&type=2
	 * Content-type: application/x-www-form-urlencoded
	 *
	 * @param url 请求地址
	 * @param queryStr  查询字符串
	 * @return
	 */
	public static String postStr(String url, String queryStr) {
		// post请求返回结果
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String result = null;
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(requestConfig);
		try {
			if (null != queryStr) {
				// 解决中文乱码问题
				StringEntity entity = new StringEntity(queryStr, "utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/json");
				httpPost.setEntity(entity);
			}
			CloseableHttpResponse response = httpClient.execute(httpPost);
			// 请求发送成功，并得到响应
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String str = "";
				try {
					// 读取服务器返回过来的json字符串数据
					result = EntityUtils.toString(response.getEntity(), "utf-8");

				} catch (Exception e) {
					logger.error("post请求提交失败:" + url, e);
				}
			}
		} catch (Exception e) {
			logger.error("post请求提交失败:" + url, e);
		} finally {
			httpPost.releaseConnection();
		}
		return result;
	}

	/**
	 * post请求传输String参数 例如：name=Jack&sex=1&type=2
	 * Content-type: application/x-www-form-urlencoded
	 *
	 * @param url 请求地址
	 * @param queryStr  查询字符串
	 * @return
	 */
	public static String postStr(String url, String queryStr, Map<String, String> headers) {
		// post请求返回结果
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String result = null;
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(requestConfig);
		try {
			// 主体参数
			if (!StringUtils.isEmpty(queryStr)) {
				// 解决中文乱码问题
				StringEntity entity = new StringEntity(queryStr, "utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/json");
				httpPost.setEntity(entity);
			}
			// 请求头
			if (!ObjectUtils.isEmpty(headers)) {
				for (Map.Entry<String, String> entry : headers.entrySet()) {
					if (!StringUtils.isEmpty(entry.getKey())) {
						httpPost.setHeader(entry.getKey(), entry.getValue());
					}
				}
			}
			CloseableHttpResponse response = httpClient.execute(httpPost);
			// 请求发送成功，并得到响应
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String str = "";
				try {
					// 读取服务器返回过来的json字符串数据
					result = EntityUtils.toString(response.getEntity(), "utf-8");

				} catch (Exception e) {
					logger.error("post请求提交失败:" + url, e);
				}
			}
		} catch (Exception e) {
			logger.error("post请求提交失败:" + url, e);
		} finally {
			httpPost.releaseConnection();
		}
		return result;
	}

	/**
	 * 发送get请求
	 *
	 * @param url
	 *            路径
	 * @return
	 */
	public static String getStr(String url) {
		// get请求返回结果
		String result = null;
		CloseableHttpClient client = HttpClients.createDefault();
		// 发送get请求
		HttpGet request = new HttpGet(url);
		request.setConfig(requestConfig);
		try {
			CloseableHttpResponse response = client.execute(request);

			// 请求发送成功，并得到响应
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// 读取服务器返回过来的json字符串数据
				HttpEntity entity = response.getEntity();
				result = EntityUtils.toString(entity, "utf-8");

			} else {
				logger.error("get请求提交失败:" + url);
			}
		} catch (IOException e) {
			logger.error("get请求提交失败:" + url, e);
		} finally {
			request.releaseConnection();
		}
		return result;
	}

}
