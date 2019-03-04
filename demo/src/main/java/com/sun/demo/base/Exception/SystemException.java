package com.sun.demo.base.Exception;

/**
 * @date 2018/03
 * @author szy
 *
 */
public class SystemException extends BaseException
{
	  /**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	
    public SystemException()
    {
        super();
    }

   
    public SystemException(final String message)
    {
        super(message);
    }

    
    public SystemException(final Exception cause)
    {
        super(cause);
    }

   
    public SystemException(final String message, final Exception cause)
    {
        super(message, cause);
    }

}
