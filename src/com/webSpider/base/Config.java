package com.webSpider.base;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.webSpider.Spider;
import com.webSpider.proxy.ProxyPool;
import com.webSpider.utils.UrlUtils;

import org.apache.http.HttpHost;

import java.util.*;

/**
 * 爬虫配置
 * @author pibigstar
 * 
 */
public class Config {

    private String domain;

    private String userAgent;

    private Map<String, String> defaultCookies = new LinkedHashMap<String, String>();

    private Table<String, String, String> cookies = HashBasedTable.create();

    private String charset;

    /**
     * startUrls is the urls the crawler to start with.
     */
    private List<Request> startRequests = new ArrayList<Request>();

    private int sleepTime = 5000;

    private int retryTimes = 0;

    private int cycleRetryTimes = 0;

    private int retrySleepTime = 1000;

    private int timeOut = 5000;

    private static final Set<Integer> DEFAULT_STATUS_CODE_SET = new HashSet<Integer>();

    private Set<Integer> acceptStatCode = DEFAULT_STATUS_CODE_SET;

    private Map<String, String> headers = new HashMap<String, String>();

    private HttpHost httpProxy;

    private ProxyPool httpProxyPool;

    private boolean useGzip = true;

    /**
     * @see com.webSpider.utils.HttpConstant.Header
     * @deprecated
     */
    public static interface HeaderConst {

        public static final String REFERER = "Referer";
    }


    static {
        DEFAULT_STATUS_CODE_SET.add(200);
    }

    /**
     * new a Site
     *
     * @return new site
     */
    public static Config me() {
        return new Config();
    }

    /**
     * Add a cookie with domain {@link #getDomain()}
     *
     * @param name name
     * @param value value
     * @return this
     */
    public Config addCookie(String name, String value) {
        defaultCookies.put(name, value);
        return this;
    }

    /**
     * Add a cookie with specific domain.
     *
     * @param domain domain
     * @param name name
     * @param value value
     * @return this
     */
    public Config addCookie(String domain, String name, String value) {
        cookies.put(domain, name, value);
        return this;
    }

    /**
     * set user agent
     *
     * @param userAgent userAgent
     * @return this
     */
    public Config setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    /**
     * get cookies
     *
     * @return get cookies
     */
    public Map<String, String> getCookies() {
        return defaultCookies;
    }

    /**
     * get cookies of all domains
     *
     * @return get cookies
     */
    public Map<String,Map<String, String>> getAllCookies() {
        return cookies.rowMap();
    }

    /**
     * get user agent
     *
     * @return user agent
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * get domain
     *
     * @return get domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * set the domain of site.
     *
     * @param domain domain
     * @return this
     */
    public Config setDomain(String domain) {
        this.domain = domain;
        return this;
    }

    /**
     * Set charset of page manually.<br>
     * When charset is not set or set to null, it can be auto detected by Http header.
     *
     * @param charset charset
     * @return this
     */
    public Config setCharset(String charset) {
        this.charset = charset;
        return this;
    }

    /**
     * get charset set manually
     *
     * @return charset
     */
    public String getCharset() {
        return charset;
    }

    public int getTimeOut() {
        return timeOut;
    }

    /**
     * set timeout for downloader in ms
     *
     * @param timeOut timeOut
     * @return this
     */
    public Config setTimeOut(int timeOut) {
        this.timeOut = timeOut;
        return this;
    }

    /**
     * Set acceptStatCode.<br>
     * When status code of http response is in acceptStatCodes, it will be processed.<br>
     * {200} by default.<br>
     * It is not necessarily to be set.<br>
     *
     * @param acceptStatCode acceptStatCode
     * @return this
     */
    public Config setAcceptStatCode(Set<Integer> acceptStatCode) {
        this.acceptStatCode = acceptStatCode;
        return this;
    }

    /**
     * get acceptStatCode
     *
     * @return acceptStatCode
     */
    public Set<Integer> getAcceptStatCode() {
        return acceptStatCode;
    }

    /**
     * get start urls
     *
     * @return start urls
     * @see #getStartRequests
     * @deprecated
     */
    @Deprecated
    public List<String> getStartUrls() {
        return UrlUtils.convertToUrls(startRequests);
    }

    public List<Request> getStartRequests() {
        return startRequests;
    }

    /**
     * Add a url to start url.<br>
     * Because urls are more a Spider's property than Site, move it to {@link Spider#addUrl(String...)}}
     *
     * @param startUrl startUrl
     * @return this
     * @see Spider#addUrl(String...)
     * @deprecated
     */
    public Config addStartUrl(String startUrl) {
        return addStartRequest(new Request(startUrl));
    }

    /**
     * Add a url to start url.<br>
     * Because urls are more a Spider's property than Site, move it to {@link Spider#addRequest(Request...)}}
     *
     * @param startRequest startRequest
     * @return this
     * @see Spider#addRequest(Request...)
     * @deprecated
     */
    public Config addStartRequest(Request startRequest) {
        this.startRequests.add(startRequest);
        if (domain == null && startRequest.getUrl() != null) {
            domain = UrlUtils.getDomain(startRequest.getUrl());
        }
        return this;
    }

    /**
     * Set the interval between the processing of two pages.<br>
     * Time unit is micro seconds.<br>
     *
     * @param sleepTime sleepTime
     * @return this
     */
    public Config setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
        return this;
    }

    /**
     * Get the interval between the processing of two pages.<br>
     * Time unit is micro seconds.<br>
     *
     * @return the interval between the processing of two pages,
     */
    public int getSleepTime() {
        return sleepTime;
    }

    /**
     * Get retry times immediately when download fail, 0 by default.<br>
     *
     * @return retry times when download fail
     */
    public int getRetryTimes() {
        return retryTimes;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    /**
     * Put an Http header for downloader. <br>
     * Use {@link #addCookie(String, String)} for cookie and {@link #setUserAgent(String)} for user-agent. <br>
     *
     * @param key   key of http header, there are some keys constant in {@link HeaderConst}
     * @param value value of header
     * @return this
     */
    public Config addHeader(String key, String value) {
        headers.put(key, value);
        return this;
    }

    /**
     * Set retry times when download fail, 0 by default.<br>
     *
     * @param retryTimes retryTimes
     * @return this
     */
    public Config setRetryTimes(int retryTimes) {
        this.retryTimes = retryTimes;
        return this;
    }

    /**
     * When cycleRetryTimes is more than 0, it will add back to scheduler and try download again. <br>
     *
     * @return retry times when download fail
     */
    public int getCycleRetryTimes() {
        return cycleRetryTimes;
    }

    /**
     * Set cycleRetryTimes times when download fail, 0 by default. <br>
     *
     * @param cycleRetryTimes cycleRetryTimes
     * @return this
     */
    public Config setCycleRetryTimes(int cycleRetryTimes) {
        this.cycleRetryTimes = cycleRetryTimes;
        return this;
    }

    public HttpHost getHttpProxy() {
        return httpProxy;
    }

    /**
     * set up httpProxy for this site
     *
     * @param httpProxy httpProxy
     * @return this
     */
    public Config setHttpProxy(HttpHost httpProxy) {
        this.httpProxy = httpProxy;
        return this;
    }

    public boolean isUseGzip() {
        return useGzip;
    }

    public int getRetrySleepTime() {
        return retrySleepTime;
    }

    /**
     * Set retry sleep times when download fail, 1000 by default. <br>
     *
     * @param retrySleepTime retrySleepTime
     * @return this
     */
    public Config setRetrySleepTime(int retrySleepTime) {
        this.retrySleepTime = retrySleepTime;
        return this;
    }

    /**
     * Whether use gzip. <br>
     * Default is true, you can set it to false to disable gzip.
     *
     * @param useGzip useGzip
     * @return this
     */
    public Config setUseGzip(boolean useGzip) {
        this.useGzip = useGzip;
        return this;
    }

    public Task toTask() {
        return new Task() {
            @Override
            public String getUUID() {
                return Config.this.getDomain();
            }

            @Override
            public Config getSite() {
                return Config.this;
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Config site = (Config) o;

        if (cycleRetryTimes != site.cycleRetryTimes) return false;
        if (retryTimes != site.retryTimes) return false;
        if (sleepTime != site.sleepTime) return false;
        if (timeOut != site.timeOut) return false;
        if (acceptStatCode != null ? !acceptStatCode.equals(site.acceptStatCode) : site.acceptStatCode != null)
            return false;
        if (charset != null ? !charset.equals(site.charset) : site.charset != null) return false;
        if (defaultCookies != null ? !defaultCookies.equals(site.defaultCookies) : site.defaultCookies != null)
            return false;
        if (domain != null ? !domain.equals(site.domain) : site.domain != null) return false;
        if (headers != null ? !headers.equals(site.headers) : site.headers != null) return false;
        if (startRequests != null ? !startRequests.equals(site.startRequests) : site.startRequests != null)
            return false;
        if (userAgent != null ? !userAgent.equals(site.userAgent) : site.userAgent != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = domain != null ? domain.hashCode() : 0;
        result = 31 * result + (userAgent != null ? userAgent.hashCode() : 0);
        result = 31 * result + (defaultCookies != null ? defaultCookies.hashCode() : 0);
        result = 31 * result + (charset != null ? charset.hashCode() : 0);
        result = 31 * result + (startRequests != null ? startRequests.hashCode() : 0);
        result = 31 * result + sleepTime;
        result = 31 * result + retryTimes;
        result = 31 * result + cycleRetryTimes;
        result = 31 * result + timeOut;
        result = 31 * result + (acceptStatCode != null ? acceptStatCode.hashCode() : 0);
        result = 31 * result + (headers != null ? headers.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Site{" +
                "domain='" + domain + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", cookies=" + defaultCookies +
                ", charset='" + charset + '\'' +
                ", startRequests=" + startRequests +
                ", sleepTime=" + sleepTime +
                ", retryTimes=" + retryTimes +
                ", cycleRetryTimes=" + cycleRetryTimes +
                ", timeOut=" + timeOut +
                ", acceptStatCode=" + acceptStatCode +
                ", headers=" + headers +
                '}';
    }

    /**
     * Set httpProxyPool, String[0]:ip, String[1]:port <br>
     *
     * @param httpProxyList httpProxyList
     * @return this
     */
    public Config setHttpProxyPool(List<String[]> httpProxyList) {
        this.httpProxyPool=new ProxyPool(httpProxyList);
        return this;
    }

    public Config enableHttpProxyPool() {
        this.httpProxyPool=new ProxyPool();
        return this;
    }

    public ProxyPool getHttpProxyPool() {
        return httpProxyPool;
    }

    public HttpHost getHttpProxyFromPool() {
        return httpProxyPool.getProxy();
    }

    public void returnHttpProxyToPool(HttpHost proxy,int statusCode) {
        httpProxyPool.returnProxy(proxy,statusCode);
    }

    public Config setProxyReuseInterval(int reuseInterval) {
        this.httpProxyPool.setReuseInterval(reuseInterval);
        return this;
    }

}
