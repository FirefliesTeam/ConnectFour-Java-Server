module.exports = function(grunt) {

    grunt.initConfig({
          concat: { 
                options:{ separator: ';'},
                foo:{ 
                    options:{},
                    src: ['js/first.js', 'js/second.js'],
                    dest: 'js/foo.js'
                },
           },
           
           shell: {
                options: {
                    stdout: true,
                    stderr: true  
                },
                server: {
                    command: 'java -cp L1.2-1.0-jar-with-dependencies.jar main.Main 8080'
                }
           },
           
            fest: {
                templates: {
                    files: [{
                        expand: true,
                        cwd: 'templates',
                        src: '*.xml',
                        dest: 'public_html/js/tmpl'
                    }],
                    options: {
                        template: function (data) {
                            return grunt.template.process(
                                'define(function () { return <%= contents %> ; });',
                                {data: data}
                            );
                        }
                    }
                }
            },
           
            watch: {
                fest: {
                    files: ['templates/*.xml'],
                    tasks: ['fest'],
                    options: {
                        interrupt: true,
                        atBegin: true
                    }
                },
                server: {
                    files: [
                        'public_html/js/**/*.js',
                        'public_html/css/**/*.css',
                        'templates/**/*.xml'
                    ],
                    options: {
                        livereload: true
                    }
                }
            },
            
            concurrent: {
                target: ['watch', 'shell'],
                options: {
                    logConcurrentOutput: true
                }
            }       
           
    });
    // Загрузка плагинов, на примере "concat".
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-shell');
    grunt.loadNpmTasks('grunt-fest');
    grunt.loadNpmTasks('grunt-concurrent');
    
    // Определение задач, default должен быть всегда.
    grunt.registerTask('default', ['concurrent']);    
    
};

