DESCRIPTION = "PNG Library"
HOMEPAGE = "http://www.libpng.org/"
LICENSE = "libpng"
SECTION = "libs"
DEPENDS = "zlib"
PRIORITY = "required"
PR = "r7"

SRC_URI = "${SOURCEFORGE_MIRROR}/libpng/libpng-${PV}.tar.bz2 \
           file://makefile_fix.patch;patch=1"

ARM_INSTRUCTION_SET = "arm"

inherit autotools binconfig pkgconfig

do_stage() {
	cp libpng.pc libpng12.pc
	install -m 644 png.h ${STAGING_INCDIR}/png.h
	install -m 644 pngconf.h ${STAGING_INCDIR}/pngconf.h
	oe_libinstall -so libpng ${STAGING_LIBDIR}/
	oe_libinstall -so libpng12 ${STAGING_LIBDIR}/
	ln -sf libpng12.so ${STAGING_LIBDIR}/libpng.so
}

do_install() {
	install -d ${D}${bindir}
	install -d ${D}${mandir}
	install -d ${D}${libdir}
	install -d ${D}${includedir}
	unset LDFLAGS
	oe_runmake 'prefix=${prefix}' 'DESTDIR=${D}' \
		   'DB=${D}${bindir}' 'DI=${D}${includedir}' \
		   'DL=${D}${libdir}' 'DM=${D}${mandir}' \
		   install
}

python do_package() {
        if bb.data.getVar('DEBIAN_NAMES', d, 1):
            bb.data.setVar('PKG_${PN}', 'libpng12', d)
        bb.build.exec_func('package_do_package', d)
}

PACKAGES =+ "${PN}12-dbg ${PN}12 ${PN}12-dev"

FILES_${PN}12-dbg += "${libdir}/libpng12*.dbg"
FILES_${PN}12 = "${libdir}/libpng12${SOLIBS}"
FILES_${PN}12-dev = "${libdir}/libpng12.* ${includedir}/libpng12 ${libdir}/pkgconfig/libpng12.pc"
FILES_${PN} = "${libdir}/lib*${SOLIBS}"
FILES_${PN}-dev = "${includedir} ${libdir}/lib*${SOLIBSDEV} ${libdir}/*.la \
        ${libdir}/*.a ${libdir}/pkgconfig \
        ${datadir}/aclocal ${bindir} ${sbindir}"
