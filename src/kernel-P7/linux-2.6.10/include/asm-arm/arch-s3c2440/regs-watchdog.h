/* linux/include/asm/arch-s3c2440/regs0watchdog.h
 *
 * Copyright (c) 2003 Simtec Electronics <linux@simtec.co.uk>
 *		      http://www.simtec.co.uk/products/SWLINUX/
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as
 * published by the Free Software Foundation.
 *
 * S3C2440 Watchdog timer control
 *
 *  Changelog:
 *    21-06-2003     BJD     Created file
 *    12-03-2004     BJD     Updated include protection
*/


#ifndef __ASM_ARCH_REGS_WATCHDOG_H
#define __ASM_ARCH_REGS_WATCHDOG_H "$Id: regs-watchdog.h,v 1.1.1.1 2005/11/16 00:55:04 yongkal Exp $"

#define S3C2440_WDOGREG(x) ((x) + S3C2440_VA_WATCHDOG)

#define S3C2440_WTCON	   S3C2440_WDOGREG(0x00)
#define S3C2440_WTDAT	   S3C2440_WDOGREG(0x04)
#define S3C2440_WTCNT	   S3C2440_WDOGREG(0x08)

/* the watchdog can either generate a reset pulse, or an
 * interrupt.
 */

#define S3C2440_WTCON_RSTEN   (0x01)
#define S3C2440_WTCON_INTEN   (1<<2)
#define S3C2440_WTCON_ENABLE  (1<<5)

#define S3C2440_WTCON_DIV16   (0<<3)
#define S3C2440_WTCON_DIV32   (1<<3)
#define S3C2440_WTCON_DIV64   (2<<3)
#define S3C2440_WTCON_DIV128  (3<<3)

#define S3C2440_WTCON_PRESCALE(x) ((x) << 8)
#define S3C2440_WTCON_PRESCALE_MASK (0xff00)

#endif /* __ASM_ARCH_REGS_WATCHDOG_H */

