package com.practica.apirest.cliente.auth;

public class JwtConfig 
{
	
	public static final String LLAVE_SECRETA = "alguna.clave.importante.12345678";
	
	public static final String RSA_PRIVATE = "-----BEGIN RSA PRIVATE KEY-----\n"
			+ "	MIIEpAIBAAKCAQEA4TUB9TqxfNLxEcTcVG6W1E5cwOcBFB9bgWD7bOjav1ypZLKG\n"
			+ "	0Va1ilj2nhfsCybGhVXSw6EEJLYizjucO+mjE0stBl/DZNXiYLA0l87p0tIdKRP0\n"
			+ "	oj8L9L/LU8/AoJfguVFh5saEkUnCfkj+x5UFOw00nSpYP9e7MWrfbqjdFtpt+snM\n"
			+ "	3UnBoD0Tha8CMZ+8KV6NI07KNXuNRKtVvP8P1y0t2uSdJudoBAhR74cszIc67yQI\n"
			+ "	oTXc8NRi0jXvYZ0X6QpC/OzIgNoWnsu4U5DwFLLb1+00rZ6pLuHNHfVvcR+DClAL\n"
			+ "	UVYYXYIVhBbR1fc2/kJSaCGImo3PxnlD2VEv1wIDAQABAoIBAQCOo4lC7lLeUai4\n"
			+ "	bWxz2buUarGayHVJbncBohB9MZrjG2h6zAxR6M6A+snw5gvuIDga2XntyD0cl3PC\n"
			+ "	FcVluRw9qx6wa9URN9rzDIezIy28BrLLgsUuQiprcVLyGaLS1H9UQPR2O4RZlVMn\n"
			+ "	s0oT1RIEDeAmL1zHyvbYXO15ZGDsCGpsKxhR3l5RQLrXHI6Q9RU5S+USZxitVTBE\n"
			+ "	1Ijn5UxDxUZmeVZScKHCrP7vTwHWKVZlcHkuSEkmm4QZmrn5j6oMh39MQYyAd3js\n"
			+ "	xi3RKtGns+u1m6ntqLnYNqe62rBIHvXGUILfDtglJ4K5y7PjI3LgM4eJTbQ68uqF\n"
			+ "	KoRWh0dZAoGBAP7+G8W+faopJAhpmXvRHutf6j2XnPCKC6A+NkrX0hPdV9EnjsVJ\n"
			+ "	OGA2XWYfvlA02BuGfnOpWycx0SmQBqLJ30CTq029Gl2DzuT+Vv+1QZNfVDoJ0lIF\n"
			+ "	bU9ze4CtZzOuQAFOu5OOKPAfoKK3trH0Fjxm7JDRbJL1LfyaMRWOIO39AoGBAOIY\n"
			+ "	xmXrLbUGR0eDeh9RTYh10X82EP5IUqCX4lw4oxTlz5nLtD93XJi74e+qE++P7KtV\n"
			+ "	rtwkCMVn2gcUkOfzzbsJWunN4zNjyq5cEmXYLJkTy1EFpVBqRMfbO3kTYg/oDfD4\n"
			+ "	p0CcTjAt2QMjBiBlLFZk5WBpNxGOKmzZpUR7xvNjAoGAH4AErkj5/StX6DIjY8hR\n"
			+ "	qYIPzcwM9yoWhZUp39UJfqq5Tn00heqlrD0LDLKfMAlXKQ9UT7V8LTtaO2gvaazG\n"
			+ "	h5O2dcQGncbKqD9DQBOEJlCAdyr/cGJ7PnVlrF+/gALmpUK0qWXGaON/VJqdVx5w\n"
			+ "	IMYWM02ru/vN0oa5yY/lQYECgYB/6nBkvReQ8UpmhVYUXdU1vSKXjCk1FAnbXdLt\n"
			+ "	RgXODu7THlKODaPPrYx4IYtB3/WQQdu6JJDpzoWlt9b7xA3DIdnTqW4/ZtgGqYSn\n"
			+ "	JhSlTnObFUygIF9liv0s1PCH+0vX53YIseXykB3VDn2q2lKpVlAHqb9EKWCLUN+V\n"
			+ "	zUmT7QKBgQDtW1CG691/ZTp6OVwPFIPQTUfDD6Kex4xWc0yOeD+fFVJyoy8va+R+\n"
			+ "	+5TN+13h58JNypMxU+taOx26LmeqlPotmJscur+2cJT0QnLK44rrp7+XfObzeOF0\n"
			+ "	QCEonqlby9cqrJin53m5h8qILrG47MMR643w7CuH6KgR9EVJfLw4WA==\n"
			+ "	-----END RSA PRIVATE KEY-----";
	
	public static final String RSA_PUBLIC = "-----BEGIN PUBLIC KEY-----\n"
			+ "	MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA4TUB9TqxfNLxEcTcVG6W\n"
			+ "	1E5cwOcBFB9bgWD7bOjav1ypZLKG0Va1ilj2nhfsCybGhVXSw6EEJLYizjucO+mj\n"
			+ "	E0stBl/DZNXiYLA0l87p0tIdKRP0oj8L9L/LU8/AoJfguVFh5saEkUnCfkj+x5UF\n"
			+ "	Ow00nSpYP9e7MWrfbqjdFtpt+snM3UnBoD0Tha8CMZ+8KV6NI07KNXuNRKtVvP8P\n"
			+ "	1y0t2uSdJudoBAhR74cszIc67yQIoTXc8NRi0jXvYZ0X6QpC/OzIgNoWnsu4U5Dw\n"
			+ "	FLLb1+00rZ6pLuHNHfVvcR+DClALUVYYXYIVhBbR1fc2/kJSaCGImo3PxnlD2VEv\n"
			+ "	1wIDAQAB\n"
			+ "	-----END PUBLIC KEY-----";













}
