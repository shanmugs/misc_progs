#include "holberton.h"

/**
 * is_delim - check if char is equal to delim
 * @c: character
 * @delim: " "
 * Return: 0 if no match, 1 if matched
 */

int is_delim(char c, const char *delim)
{
	while (delim && *delim)
	{
		if (c == *delim)
		{
			return (1);
		}
		++delim;
	}
	return (0);
}

/**
 * _strtok - mimics strtok, which tokenizes a string and turn to array
 * @src: string from getline
 * @delim: " ";
 * Return: individual token in array format
 */

char *_strtok(char *src, const char *delim)
{
	static char *s;
	static unsigned int i;
	char *result = NULL;
	int is_space = 0;

	if (src)
	{
		s = src;
		for (i = 0; s[i]; i++)
		{
			if (is_delim(s[i], delim))
				s[i] = '\0';
		}
	}

	if (s == NULL || *s == '\0')
		return (NULL);
	result = s;
	i = _strlen(s);
	if (s[i] == '\0' && _strlen(s) > 0)
		is_space = 1;
	s = s + _strlen(s) + is_space;
	return (result);
}

/**
 *ctrlc - control C handler
 *@signum: the signal number received
 *
 *Return: void
 */
void ctrlc(int signum)
{
	(void)signum;

	write(STDOUT_FILENO, "\n#cisfun$ ", 10);
}


/**
 * get_line - stores into malloced buffer the user's command into shell
 * @str: buffer
 * Return: number of characters read
 */
ssize_t get_line(char **str)
{
	ssize_t i = 0, size = 0, t = 0, t2 = 0, n = 0;
	char buff[1024];

	/* read while there's stdin greater than buffsize; -1 to add a '\0' */
	while (t2 == 0 && (i = read(STDIN_FILENO, buff, 1024 - 1)))
	{
		if (i == -1) /* check if read errored */
			return (-1);

		buff[i] = '\0'; /* terminate buff with \0 to use with _strcat */

		n = 0; /* last loop if \n is found in the stdin read */
		while (buff[n] != '\0')
		{
			if (buff[n] == '\n')
				t2 = 1;
			n++;
		}

		/* copy what's read to buff into get_line's buffer */
		if (t == 0) /* malloc the first time */
		{
			i++;
			*str = malloc(sizeof(char) * i);
			*str = _strcpy(*str, buff);
			size = i;
			t = 1;
		}
		else /* _realloc via _strcat with each loop */
		{
			size += i;
			*str = _strcat(*str, buff);
		}
	}
	return (size);
}
/**
 *  _strdup - duplicates string
 *  @str: string to duplicate
 *  Return: pointer to duplicated string in allocated memory
 */
char *_strdup(char *str)
{
	char *duplicate_str;
	int i, len = 0;

	if (str == NULL)
		return (NULL);
	while (str[len])
		len++;
	len++; /* add null terminator to length */
	duplicate_str = malloc(sizeof(char) * len);
	if (duplicate_str == NULL)
		return (NULL);
	i = 0;
	while (i < len)
	{
		duplicate_str[i] = str[i];
		i++;
	}
	return (duplicate_str);
}
